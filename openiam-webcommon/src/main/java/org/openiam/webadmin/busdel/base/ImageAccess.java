package org.openiam.webadmin.busdel.base;


import java.util.*;

import diamelle.catalog.product.*;

/**
 * <p>Title: Diamelle Technologies Web Site</p>
 * <p>Description: Powered by Diamelle EJB Components</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Diamelle</p>
 * @author Arun
 * @version 1.0
 */

public class ImageAccess extends NavigationAccess {

    ImageHome imageHome = null;
    ImageManager imageMgr = null;

    public ImageAccess() {
      try{
        ImageManagerHome imageMgrHome = ( ImageManagerHome ) this.getHome( "ImageManager");
        imageMgr = imageMgrHome.create();
        imageHome = ( ImageHome ) getHome( "Image");


      }
      catch(Exception e){
        e.printStackTrace();
      }
    }


    public void addImage(ImageValue imageVal)throws Exception{
      String imageId = this.getNextId("IMAGE_ID");
      imageVal.setImageId(imageId);
      imageHome.create(imageVal);

    }

    public void removeImage(String imageId)throws Exception{
      Image image = imageHome.findByPrimaryKey(new ImageKey(imageId));
      image.remove();
    }

    public void saveImage(ImageValue imageValue)throws Exception{
      Image image = imageHome.findByPrimaryKey(new ImageKey(imageValue.getImageId()));
      image.setImageValue(imageValue);
    }

    public ImageValue getImage( String imageId)throws Exception {
       Image image = imageHome.findByPrimaryKey(new ImageKey(imageId));
       return(image.getImageValue());
    }

    public List getAllImageTypes() throws java.rmi.RemoteException{
      return imageMgr.getAllImageTypes();
    }

    public List getImages(String imageType) throws java.rmi.RemoteException{
      return imageMgr.getImagesByImageType(imageType);
    }

     public List getImagesByType ( String imgType ) throws java.rmi.RemoteException{
       return imageMgr.getImagesByImageType(imgType);
    }



}
