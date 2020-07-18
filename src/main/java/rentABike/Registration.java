package rentABike;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Registration {
    private String name;
    private String surname;
    private String dateOfBirth;
    private String email;
    private String password;

    public Registration(String name, String surname, String dateOfBirth, String email, String password) throws NoSuchAlgorithmException {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = HashPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = HashPassword(password);
    }

    public void saveUser() {

        JSONParser jsonParser = new JSONParser();

        try {
            Path currentRelativePath = Paths.get("");
            String projectPath = currentRelativePath.toAbsolutePath().toString();

            Object obj = jsonParser.parse(new FileReader(projectPath + "/src/main/java/rentABike/UsersData.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("users");

            JSONObject user = new JSONObject();
            user.put("name", this.name);
            user.put("surname", this.surname);
            user.put("dateOfBirth", this.dateOfBirth);
            user.put("email", this.email);
            user.put("password", this.password);

            jsonArray.add(user);

            FileWriter file = new FileWriter(projectPath + "/src/main/java/rentABike/UsersData.json");
            file.write(jsonObject.toJSONString());
            file.flush();
            file.close();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        /*Path currentRelativePath = Paths.get("");
        String projectPath = currentRelativePath.toAbsolutePath().toString();
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("surname", this.surname);
        obj.put("dateOfBirth", this.dateOfBirth);
        obj.put("email", this.email);
        obj.put("password", this.password);

        try (FileWriter file = new FileWriter(projectPath +"/src/main/java/rentABike/UsersData.json")){
            file.write(obj.toJSONString());
        } catch (IOException e){
            e.printStackTrace();
        }*/


    }

    public static String HashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b1 : b) {
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        return sb.toString();

    }
}

