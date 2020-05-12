
/**
* Homework 5 <br>
* Simeng Hao, sh4aj <br>
* Source: offcial java API<br>
*/
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class PhotoViewer extends JFrame {

    private static final long serialVersionUID = 1L;
    private PhotographContainer imageAlbum;
    public ArrayList<ImageIcon> imageIcons;
    private ImageIcon currentImgIcon;
    private Photograph currentPhoto;
    private int currentImgIconIndex = 0;

    private final int[] DEFAULT_GBL_WIDTHS = new int[] { 250, 450 };
    private final int[] DEFAULT_GBL_HEIGHTS = new int[] { 100, 400, 100 };
    private JFrame frame;
    private JLabel lblPhoto;
    private JPanel pnlThumbnails;
    private ArrayList<JLabel> lblThumbnails;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        ArrayList<Photograph> photos = setUpPhotographs();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    PhotoViewer myViewer = new PhotoViewer(photos);
                    myViewer.frame.setVisible(true);
                    // myViewer.frame.pack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Specifies the photos to be viewed in this viewer
     */
    private static ArrayList<Photograph> setUpPhotographs() {
        ArrayList<Photograph> photos = new ArrayList<>();
        Photograph p1 = new Photograph("./images/1.jpg", "Drone", "2010-01-01", 1);
        Photograph p2 = new Photograph("./images/2.jpg", "Phone", "2011-02-02", 2);
        Photograph p3 = new Photograph("./images/3.jpg", "Voyage", "2013-03-03", 3);
        Photograph p4 = new Photograph("./images/4.jpg", "Portait", "2014-04-04", 4);
        Photograph p5 = new Photograph("./images/5.jpg", "Fried", "2015-05-05", 5);
        photos.add(p2);
        photos.add(p1);
        photos.add(p3);
        photos.add(p4);
        photos.add(p5);
        return photos;
    }

    /**
     * Create the application.
     */
    public PhotoViewer(ArrayList<Photograph> photos) {
        imageAlbum = new Album("");
        for (Photograph item : photos) {
            this.imageAlbum.addPhoto(item);
        }
        Collections.sort(imageAlbum.photos);
        this.imageIcons = generateImgIconsFromAlbum(this.imageAlbum);
        updateCurrentImgIcon();
        this.lblThumbnails = new ArrayList<>();
        initialize();
    }

    private void updateCurrentImgIcon() {
        currentImgIcon = this.imageIcons.get(currentImgIconIndex);
        currentPhoto = this.imageAlbum.photos.get(currentImgIconIndex);
    }

    private void cycleImgIconIndex(boolean next) {
        int newIndex = this.currentImgIconIndex;
        if (next) {
            newIndex += 1;
        } else {
            newIndex -= 1;
        }
        newIndex = Math.floorMod(newIndex, this.imageIcons.size());
        this.currentImgIconIndex = newIndex;
    }

    private ArrayList<ImageIcon> generateImgIconsFromAlbum(PhotographContainer inputImageAlbum) {
        ArrayList<ImageIcon> result = new ArrayList<>();
        for (int i = 0; i < imageAlbum.numPhotographs(); i++) {
            Photograph photo = inputImageAlbum.getPhotos().get(i);
            ImageIcon icon = new ImageIcon(photo.getImagefile().getPath());
            int iconWidth, iconHeight;
            try {
                GridBagLayout layout = (GridBagLayout) this.frame.getContentPane().getLayout();
                iconWidth = layout.columnWidths[1];
                iconHeight = layout.rowHeights[1];
            } catch (NullPointerException e) {
                iconWidth = DEFAULT_GBL_WIDTHS[1];
                iconHeight = DEFAULT_GBL_HEIGHTS[1];
            }
            resizeIconProportionately(icon, iconWidth, iconHeight);
            result.add(icon);
        }
        return result;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = DEFAULT_GBL_WIDTHS;
        gridBagLayout.rowHeights = DEFAULT_GBL_HEIGHTS;
        gridBagLayout.columnWeights = new double[] { Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { Double.MIN_VALUE };
        frame.getContentPane().setLayout(gridBagLayout);
        ArrayList<ComponentWithConstraints> components = createComponentsArray();
        addComponents(frame, components);

    }

    private ArrayList<ComponentWithConstraints> createComponentsArray() {
        ArrayList<ComponentWithConstraints> result = new ArrayList<>();
        result.add(createToolbar());
        result.add(createThumbnailsScrollPane());
        result.add(createPhotoLabel());
        result.add(createRater());
        return result;
    }

    private ComponentWithConstraints createToolbar() {
        JPanel toolbar = new JPanel(new GridLayout(1, 6));
        GridBagConstraints toolbarConstraints = new GridBagConstraints();
        toolbarConstraints.gridx = 0;
        toolbarConstraints.gridy = 0;
        toolbarConstraints.gridheight = 1;
        toolbarConstraints.gridwidth = 2;
        toolbarConstraints.weightx = 1;
        toolbarConstraints.weighty = 0;
        for (Component item : createToolbarButtons()) {
            toolbar.add(item);
        }
        return new ComponentWithConstraints(toolbar, toolbarConstraints);
    }

    private ArrayList<Component> createToolbarButtons() {
        ArrayList<Component> result = new ArrayList<>();
        result.add(setUpExitButton());
        result.add(setUpPrevButton());
        result.add(setUpNextButton());
        result.add(setUpSortByDateButton());
        result.add(setUpSortByCaptionButton());
        result.add(setUpSortByRatingButton());
        return result;
    }

    private JButton setUpExitButton() {
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });
        return exitButton;
    }

    private JButton setUpPrevButton() {
        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cycleImgIconIndex(false);
                updateLblPhoto();
            }

        });
        return prevButton;
    }

    private JButton setUpNextButton() {
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cycleImgIconIndex(true);
                updateLblPhoto();
            }

        });
        return nextButton;
    }

    private JButton setUpSortByDateButton() {
        JButton sortByDateButton = new JButton("Sort by date");
        sortByDateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(PhotoViewer.this.imageAlbum.getPhotos());
                updatePanelAfterSorting();
            }

        });
        return sortByDateButton;
    }

    private void updatePanelAfterSorting() {
        this.imageIcons = generateImgIconsFromAlbum(this.imageAlbum);
        this.currentImgIconIndex = this.imageAlbum.getPhotos().indexOf(currentPhoto);
        updateThumbnails(this.pnlThumbnails);
    }

    private JButton setUpSortByCaptionButton() {
        JButton sortByCaptionButton = new JButton("Sort by caption");
        sortByCaptionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(PhotoViewer.this.imageAlbum.getPhotos(), new CompareByCaption());
                updatePanelAfterSorting();
            }

        });
        return sortByCaptionButton;
    }

    private JButton setUpSortByRatingButton() {
        JButton sortByRatingButton = new JButton("Sort by rating");
        sortByRatingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Collections.sort(PhotoViewer.this.imageAlbum.getPhotos(), new CompareByRating());
                updatePanelAfterSorting();
            }

        });
        return sortByRatingButton;
    }

    private ComponentWithConstraints createThumbnailsScrollPane() {
        JScrollPane scrThumbnails = new JScrollPane();
        JPanel pnlThumbnails = createThumbnailsPanel();
        scrThumbnails.setViewportView(pnlThumbnails);
        GridBagConstraints scrThumbnailsConstraints = new GridBagConstraints();
        scrThumbnailsConstraints.fill = GridBagConstraints.BOTH;
        scrThumbnailsConstraints.gridx = 0;
        scrThumbnailsConstraints.gridy = 1;
        scrThumbnailsConstraints.gridheight = 2;
        scrThumbnailsConstraints.gridwidth = 1;
        scrThumbnailsConstraints.weightx = 0;
        scrThumbnailsConstraints.weighty = 1;
        return new ComponentWithConstraints(scrThumbnails, scrThumbnailsConstraints);
    }

    private JPanel createThumbnailsPanel() {
        JPanel thumbnailsPanel = new JPanel();
        thumbnailsPanel.setLayout(new GridLayout(this.imageAlbum.numPhotographs(), 1));
        updateThumbnails(thumbnailsPanel);
        this.pnlThumbnails = thumbnailsPanel;
        return thumbnailsPanel;
    }

    private void updateThumbnails(JPanel thumbnailsPanel) {
        thumbnailsPanel.removeAll();
        lblThumbnails = new ArrayList<>();
        for (Photograph photo : this.imageAlbum.photos) {
            String info = photo.createStrThumbnailInfo();
            JLabel lblThumbnail = new JLabel(info);
            addIconToLbl(lblThumbnail, photo);
            lblThumbnail.setHorizontalTextPosition(SwingConstants.CENTER);
            lblThumbnail.setVerticalTextPosition(SwingConstants.BOTTOM);
            lblThumbnail.addMouseListener(new MouseAdapter() {

                public void mouseClicked(MouseEvent e) {
                    JLabel clickedLabel = (JLabel) e.getSource();
                    PhotoViewer.this.currentImgIconIndex = PhotoViewer.this.lblThumbnails.indexOf(clickedLabel);
                    updateLblPhoto();
                }
            });
            lblThumbnails.add(lblThumbnail);
            thumbnailsPanel.add(lblThumbnail);
        }
    }

    private void updateLblPhoto() {
        updateCurrentImgIcon();
        this.lblPhoto.setIcon(currentImgIcon);
    }

    private void addIconToLbl(JLabel lblThumbnail, Photograph photo) {
        ImageIcon imgIcon = new ImageIcon(photo.getImagefile().getPath());
        resizeIconProportionately(imgIcon, 200, 10000);
        lblThumbnail.setIcon(imgIcon);
    }

    private ComponentWithConstraints createPhotoLabel() {
        JLabel photoLabel = new JLabel();
        photoLabel.setIcon(this.currentImgIcon);
        GridBagConstraints photoConstraints = new GridBagConstraints();
        photoConstraints.gridx = 1;
        photoConstraints.gridy = 1;
        photoConstraints.weightx = 1;
        photoConstraints.weighty = 0.5;
        this.lblPhoto = photoLabel;
        // resizePhotoLabel(this.lblPhoto);
        return new ComponentWithConstraints(photoLabel, photoConstraints);
    }

    /**
     * resize imgIcon to fit in a rectangle of desired size without changing it's
     * proportions.
     */
    private void resizeIconProportionately(ImageIcon imgIcon, int newWidth, int newHeight) {
        double widthProportion = (double) newWidth / imgIcon.getIconWidth();
        double heightProportion = (double) newHeight / imgIcon.getIconHeight();
        double scaleCoefficient = widthProportion < heightProportion ? widthProportion : heightProportion;
        scaleImgIcon(imgIcon, scaleCoefficient);
    }

    /**
     * scales an ImageIcon by multiplying its dimensions by scaleCoefficient
     */
    private void scaleImgIcon(ImageIcon imgIcon, double scaleCoefficient) {
        Image img = imgIcon.getImage();
        int newWidth = (int) (imgIcon.getIconWidth() * scaleCoefficient);
        int newHeight = (int) (imgIcon.getIconHeight() * scaleCoefficient);
        Image newImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        imgIcon.setImage(newImg);
    }

    private ComponentWithConstraints createRater() {
        JPanel pnlRater = new JPanel(new GridLayout(1, 6));
        pnlRater.add(new JLabel("Rate: "));
        addRaterButtons(pnlRater);
        GridBagConstraints raterConstraints = new GridBagConstraints();
        raterConstraints.gridx = 1;
        raterConstraints.gridy = 2;
        raterConstraints.weightx = 1;
        raterConstraints.weighty = 0.5;
        return new ComponentWithConstraints(pnlRater, raterConstraints);
    }

    private void addRaterButtons(JPanel pnlRater) {
        for (int i = 1; i <= 5; i++) {
            String buttonText = String.valueOf(i);
            JButton btnRating = new JButton(buttonText);
            btnRating.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();
                    int newRating = Integer.valueOf(clickedButton.getText());
                    currentPhoto.setRating(newRating);
                    updateThumbnailText(currentImgIconIndex, currentPhoto.createStrThumbnailInfo());
                }
            });
            pnlRater.add(btnRating);
        }
    }

    private void updateThumbnailText(int indexOfThumbnail, String newText) {
        this.lblThumbnails.get(indexOfThumbnail).setText(newText);
    }

    private void addComponents(JFrame target, ArrayList<ComponentWithConstraints> components) {
        for (ComponentWithConstraints item : components) {
            target.add(item.component, item.constraints);
        }
    }

    class ComponentWithConstraints {
        public GridBagConstraints constraints;
        public Component component;

        public ComponentWithConstraints(Component component, GridBagConstraints constraints) {
            this.component = component;
            this.constraints = constraints;
        }
    }

}
