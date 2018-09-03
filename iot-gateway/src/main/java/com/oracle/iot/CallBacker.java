package com.oracle.iot;

import oracle.iot.client.DeviceModel;
import oracle.iot.client.device.DirectlyConnectedDevice;
import oracle.iot.client.device.VirtualDevice;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.PostConstruct;
import java.io.*;
import java.security.GeneralSecurityException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


@RestController
public class CallBacker {

    //method used to simulate curl and communicate with the bot
    private void sendCurl(String targetUrl) throws Exception {

        //if the url is null set the default one
        if(null==targetUrl){
            targetUrl = Params.CONFIG_URL_BOT_START;
        }

        URL obj = new URL(targetUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        //headers
        con.setRequestProperty("User-Agent", Params.CONFIG_DEFAULT_USER_AGENT);

        int responseCode = con.getResponseCode();


        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //results
        System.out.println("\nSending 'GET' request to URL : " + targetUrl);
        System.out.println("Response Code : " + responseCode);

    }


    //directly connected devices and virtual devices to communicate with oracle cloud

    DirectlyConnectedDevice dcd = null;
    VirtualDevice virtualDevice = null;

    DirectlyConnectedDevice dcd2 = null;
    VirtualDevice virtualDevice2 = null;

    DirectlyConnectedDevice dcd3 = null;
    VirtualDevice virtualDevice3 = null;

    DirectlyConnectedDevice dcd_temp = null;
    VirtualDevice virtualDevice_temp = null;

    DirectlyConnectedDevice dcd_temp2 = null;
    VirtualDevice virtualDevice_temp2 = null;

    DirectlyConnectedDevice dcd_temp3 = null;
    VirtualDevice virtualDevice_temp3 = null;

    DirectlyConnectedDevice dcd_touch = null;
    VirtualDevice virtualDevice_touch = null;

    DirectlyConnectedDevice dcd_touch2 = null;
    VirtualDevice virtualDevice_touch2 = null;

    DirectlyConnectedDevice dcd_touch3 = null;
    VirtualDevice virtualDevice_touch3 = null;



    //Escucha el dispositivo python y lo envia al iot cloud
    @RequestMapping("/send_light")
    public void send_light(@RequestParam(value="light", defaultValue="90", required = true) String light,
                           @RequestParam(value="bot", defaultValue="1", required = true) int bot) {
        try {
            float l = Float.parseFloat(light);
            switch(bot){
                case 1:
                    virtualDevice.update().set(Params.LIGHT_ATTRIBUTE, l).finish();
                    break;
                case 2:
                    virtualDevice2.update().set(Params.LIGHT_ATTRIBUTE, l).finish();
                    break;
                case 3:
                    virtualDevice3.update().set(Params.LIGHT_ATTRIBUTE, l).finish();
                    break;
            }
        }catch (Exception e){
            System.out.println("ERROR SEND" + e.getMessage());
        }
    }

    //Escucha el dispositivo python y lo envia al iot cloud
    @RequestMapping("/send_temp")
    public void send_temp(@RequestParam(value="temp", defaultValue="90", required = true) String temp,
                          @RequestParam(value="bot", defaultValue="1", required = true) int bot) {
        try {
            float temperature = Float.parseFloat(temp);
            System.out.println("TEMP: "+temp);
            switch(bot){
                case 1:
                    virtualDevice_temp.update().set(Params.TEMPERATURE_ATTRIBUTE, temperature).finish();
                    break;
                case 2:
                    virtualDevice_temp2.update().set(Params.TEMPERATURE_ATTRIBUTE, temperature).finish();
                    break;
                case 3:
                    virtualDevice_temp3.update().set(Params.TEMPERATURE_ATTRIBUTE, temperature).finish();
                    break;
            }
        }catch (Exception e){
            System.out.println("ERROR SEND" + e.getMessage());
        }
    }

    //Escucha el dispositivo python y lo envia al iot cloud
    @RequestMapping("/send_touch")
    public void send_touch(@RequestParam(value="touch", defaultValue="1", required = true) String t,
                           @RequestParam(value="bot", defaultValue="1", required = true) int bot) {

        try {
            float to = Float.parseFloat(t);
            switch(bot){
                case 1:
                    virtualDevice_touch.update().set(Params.TOUCH_ATTRIBUTE, to).finish();
                    break;
                case 2:
                    virtualDevice_touch2.update().set(Params.TOUCH_ATTRIBUTE, to).finish();
                    break;
                case 3:
                    virtualDevice_touch3.update().set(Params.TOUCH_ATTRIBUTE, to).finish();
                    break;
            }
        }catch (Exception e){
            System.out.println("ERROR SEND" + e.getMessage());
        }
    }

    //method to init all the hardware devices and set to listen the methods*/
    @PostConstruct
    public void init() throws GeneralSecurityException, IOException {

        //Get remote configurations from WEDO API
        String contentFile = getFile();
        //Get the array list parsed
        ArrayList<FileDevice> FileDevices = parseFile(contentFile);
        //Get provision data from the API REST and store the data
        for(int cont = 0; cont < FileDevices.size(); cont++){
            //for each provision data
            String textoToStore = getProvisionData(FileDevices.get(cont).getDemozone(),FileDevices.get(cont).getDeviceid());
            saveFile(textoToStore,FileDevices.get(cont).getDeviceid());
        }

        ///////////////////INIT DEVICES BOT1////////////////////////////////////////////////////////////////////////////
        //LIGHT SENSOR BOT1
        /*dcd = new DirectlyConnectedDevice(Params.CONFIG_PATH_LIGHT, Params.CONFIG_PASSWORD);
        DeviceModel dcdModel = dcd.getDeviceModel(Params.ASSET_MONITORING_DEVICE_MODEL_URN_LIGHT);
        // Activate the device
        if (!dcd.isActivated()) {
            dcd.activate(Params.ASSET_MONITORING_DEVICE_MODEL_URN_LIGHT);
        }
        System.out.println("STARTED LIGHT BOT1");
        virtualDevice = dcd.createVirtualDevice(dcd.getEndpointId(), dcdModel);
        virtualDevice.setCallable(Params.METHOD_NAME_LIGHT, new VirtualDevice.Callable<Integer>() {
            public void call(VirtualDevice var1, Integer var2){
                System.out.println("RECIBIDO LIGHT" + var2);
            }
        });
*/
        //TEMP SENSOR BOT1
        dcd_temp = new DirectlyConnectedDevice(Params.CONFIG_PATH_TEMP, Params.CONFIG_PASSWORD);
        DeviceModel dcdModel_t = dcd_temp.getDeviceModel(Params.ASSET_MONITORING_DEVICE_MODEL_URN_TEMP);
        // Activate the device
        if (!dcd_temp.isActivated()) {
            dcd_temp.activate(Params.ASSET_MONITORING_DEVICE_MODEL_URN_TEMP);
        }
        System.out.println("STARTED TEMP BOT1");
        virtualDevice_temp = dcd_temp.createVirtualDevice(dcd_temp.getEndpointId(), dcdModel_t);
        virtualDevice_temp.setCallable(Params.METHOD_NAME_TEMP, new VirtualDevice.Callable<Integer>() {
            public void call(VirtualDevice var3, Integer remoteComand){
                System.out.println("RECIBIDO TEMP " + remoteComand);
                //Defined actions to control the robot arm
                switch(remoteComand){
                    case 1:
                        try {
                            //SEND THE STOP COMMAND TO THE ROBOT
                            sendCurl(Params.CONFIG_URL_BOT_STOP);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }

            }
        });
        //TOUCH SENSOR BOT1
/*
        dcd_touch = new DirectlyConnectedDevice(Params.CONFIG_PATH_TOUCH, Params.CONFIG_PASSWORD);
        DeviceModel dcdModel_to = dcd_touch.getDeviceModel(Params.ASSET_MONITORING_DEVICE_MODEL_URN_TOUCH);
        // Activate the device
        if (!dcd_touch.isActivated()) {
            dcd_touch.activate(Params.ASSET_MONITORING_DEVICE_MODEL_URN_TOUCH);
        }
        System.out.println("STARTED TOUCH BOT1");
        virtualDevice_touch = dcd_touch.createVirtualDevice(dcd_touch.getEndpointId(), dcdModel_to);
        virtualDevice_touch.setCallable(Params.METHOD_NAME_TOUCH, new VirtualDevice.Callable<Integer>() {
            public void call(VirtualDevice var5, Integer var6){
                System.out.println("RECIBIDO TOUCH " + var6);
            }
        });
*/

    }

    /*	handle the HTTP calls to the API REST
     *	@param String location
     *  @return ArrayList<FileDevice>
     *  Method GET
     */
    private ArrayList<FileDevice> parseFile(String location){

        //ArrayList of file devices
        ArrayList<FileDevice> parsed = new ArrayList<FileDevice>();

        int cont = 0;
        //loop for each deviceid
        while(cont < Params.CONFIG_FILE_NAMES.length){

            //aux device
            FileDevice aux = new FileDevice();

            //setters
            aux.setDemozone(location);
            aux.setDeviceid(Params.CONFIG_FILE_NAMES[cont]);

            //add the object
            parsed.add(aux);
            cont++;
        }

        return parsed;
    }

    /*	Read the country file stored in the file system
     *  Method GET
     */
    private String getFile(){
        String data = "",aux = "";
        File f = new File(Params.CONFIG_PATH_DEMOZONE_FILE);
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader buffer = new BufferedReader(fr);

        //readlines
        try{
            while((aux = buffer.readLine()) != null){
                data = data + aux;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //replacement of new lines
        // \n Unix
        // \r\n Windows

        data.replaceAll("\r\n","");
        data.replaceAll("\n","");
        data.replaceAll(" ","");

        //finally
        return data;
    }


    /*	handle HTTP calls to the API REST
     *	@param String demoZone
     *	@param String deviceId
     *  @return String output
     *  Method GET
     */
    private String getProvisionData( String demoZone, String deviceId){
        String output = null;
        System.out.println("INFO: STARTING CONNECTION");
        try{
            String finalUrl = Params.CONFIG_URL_MULTI_TENANCY_BASE_PROXY+demoZone+"/"+deviceId;
            URL urlp = new URL(finalUrl);

            // New url connection
            HttpURLConnection conn = (HttpURLConnection) urlp.openConnection();

            // Headers
            conn.setRequestProperty("Accept","application/json");

            // Method
            conn.setRequestMethod("GET");

            //If the app get the code 200(OK) continue
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("ERROR CONEXION CODE" + conn.getResponseMessage());
            }
            // Buffer response
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            // Get the output
            output = br.readLine();

            if(output.isEmpty()) {
                throw new Exception("ERROR IN API RESPONSE EMPTY"+output);
            }

            // Close connection
            conn.disconnect();

            //Parse received data
            //JSON format{"provisiondata":"DATA"}
            JSONObject provisionDataJson = new JSONObject(output);
            output = provisionDataJson.getString(Params.CONFIG_JSON_ATRTIBUTE);

        }catch(Exception e){
            System.out.println("ERROR IN API RESPONSE");
            e.printStackTrace();
        }
        return output;
    }

    /*  Rewrite the config files of all sensors
     *	@param String text
     *	@param String file
     */
    private void saveFile(String text,String file) throws IOException {
        //only writes when text is not null
        if(null!=text) {
            //Path
            String path = Params.CONFIG_PATH_FILES + file;
            //buffer
            BufferedWriter w = new BufferedWriter(new FileWriter(path));
            //write and close operations
            w.write(text);
            w.close();
        }
    }
}
