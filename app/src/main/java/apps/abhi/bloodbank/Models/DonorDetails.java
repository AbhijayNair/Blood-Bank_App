package apps.abhi.bloodbank.Models;

public class DonorDetails {
    private String name,phone,location,blood,status;
    public DonorDetails(){}
    public DonorDetails(String name,String location,String phone,String blood,String status){
        this.blood=blood;
        this.location = location;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
