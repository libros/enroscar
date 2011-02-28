package com.stanfy.images;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.stanfy.images.model.CachedImage;

/**
 * Images manager context. Supported views: {@link ImageView}, {@link CompoundButton}.
 * @param <T> cached image type
 * @author Roman Mazur - Stanfy (http://www.stanfy.com)
 */
public class ImagesManagerContext<T extends CachedImage> {

  /** Images DAO. */
  private ImagesDAO<T> imagesDAO;

  /** Downloader. */
  private Downloader downloader;

  /** Images manager. */
  private ImagesManager<T> imagesManager;

  /** @return the imagesDAO */
  public ImagesDAO<T> getImagesDAO() { return imagesDAO; }
  /** @param imagesDAO the imagesDAO to set */
  public void setImagesDAO(final ImagesDAO<T> imagesDAO) { this.imagesDAO = imagesDAO; }
  /** @return the downloader */
  public Downloader getDownloader() { return downloader; }
  /** @param downloader the downloader to set */
  public void setDownloader(final Downloader downloader) { this.downloader = downloader; }
  /** @return the imagesManager */
  public ImagesManager<T> getImagesManager() { return imagesManager; }
  /** @param imagesManager the imagesManager to set */
  public void setImagesManager(final ImagesManager<T> imagesManager) { this.imagesManager = imagesManager; }

  private <VT extends View> void populate(final VT view, final String url) {
    final ImagesDAO<T> imagesDAO = this.imagesDAO;
    final Downloader downloader = this.downloader;
    final ImagesManager<T> imagesManager = this.imagesManager;
    if (imagesDAO != null && downloader != null && imagesManager != null) {
      imagesManager.populateImage(view, url, imagesDAO, downloader);
    }
  }

  public void populateImageView(final ImageView view, final String url) {
    populate(view, url);
  }
  public void populateCompoundButton(final CompoundButton view, final String url) {
    populate(view, url);
  }

}
