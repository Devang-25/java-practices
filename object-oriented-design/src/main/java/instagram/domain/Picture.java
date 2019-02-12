package instagram.domain;


public class Picture{ 
    private int machineID; 
    private int pictureID; 
    private String photoPath; 
   
    public Picture(int machineID, int pictureID, String photoPath){ 
        this.machineID = machineID; 
        this.pictureID = pictureID; 
        this.photoPath = photoPath; 
    } 
   
    public int getMachineID(){ 
        return machineID; 
    } 
   
    public void setMachineID(int machineID){ 
        this.machineID = machineID; 
    } 
   
    public int getPictureID(){ 
        return pictureID; 
    } 
   
    public String getPhotoPath(){ 
        return photoPath; 
    } 
   
    public void setPhotoPath(String photoPath){ 
        this.photoPath = photoPath; 
    } 
} 
