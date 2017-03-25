/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uavsim;

/**
 *
 * @author Administrator
 */
public class test {
    public static void main(String[] args) {
        
       String data="35.694160,51.294510\n" +
                    "35.708414,51.334635\n" +
                    "35.708065,51.351244\n" +
                    "35.721132,51.345815\n" +
                    "35.692383,51.337304\n" +
                    "35.694160,51.294510\n" +
                    "35.667521,51.360706\n" +
                    "35.699276,51.350192\n" +
                    "35.689483,51.390060\n" +
                    "35.667491,51.360127\n" +
                    "35.408809,51.155108\n" +
                    "35.372791,51.381379\n" +
                    "35.485251,51.289712\n" +
                    "35.285401,51.284905\n" +
                    "35.437433,51.617585\n" +
                    "35.413933,51.142426\n" +
                    "35.534438,50.982781\n" +
                    "35.713045,51.300355\n" +
                    "35.600346,51.493302\n" +
                    "35.457291,51.592522\n" +
                    "35.645540,51.383414\n" +
                    "35.643181,51.437674\n" +
                    "35.674354,51.398192\n" +
                    "35.705096,51.301632\n" +
                    "35.653023,51.270738";
        
        String emad[] = data.split("\n");

        String res="";
        
        for (int i = 1; i <=5 ; i++) {
            res +=  "     static ArrayList<Trackpoint> UAV_"+(i+1)+"(){\n" +
                    "        ArrayList<Trackpoint> targetWaypoint=new ArrayList<>();\n" +
                    "        ";
            
            for(int j=1;j<=5;j++)
            {
                res += "targetWaypoint.add(new Trackpoint(50, new Latitude("+emad[5*(i-1)+(j-1)].split(",")[0]+"), new Longitude("+emad[5*(i-1)+(j-1)].split(",")[1]+"), new Date(),\"UAV #"+(i+1)+" - "+j+"\"));\n";    
            }
            res+="   return targetWaypoint;\n" +
                 "    }\n\n\n\n";
        }
        
        System.out.println(res);
        
//        Trackpoint t1=new Trackpoint(10, new Latitude(35.72087288), new Longitude(51.4352417),new Date());
//        Model.Component.Sensor.Camera camera = new Camera(t1, 400, 400);
//        
//        Scanner in = new Scanner(System.in);
//        int buffer=0;
//        for(int i=0;i<30;i++){
//            try {
//                t1.getLatitude().setMinutes(t1.getLatitude().getMinutes()+buffer);
//                
//                System.out.println(t1.getLatitude().getDecimal()+" = "+t1.getLatitude().getSeconds());
//                camera.setPosition(t1);
//                camera.refresh();
//                BufferedImage tmp =  (BufferedImage) camera.getImage();
//                ImageIO.write(tmp,"jpg",new File("c:\\out-"+i+".jpg"));
//                
//            } catch (IOException ex) {
//                Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            buffer++;
//        }
    }
}
