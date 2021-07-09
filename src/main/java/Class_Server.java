
import java.net.*;
import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gersson
 */
public class Class_Server 
{
    
    public static void main(String[] args)
    {
    
        ServerSocket Sck_Server; //server
        Socket Sck; //connection
        
        DataInputStream ValorEntrada;//input
        DataOutputStream ValorSalida;
        
        BufferedInputStream BuffInput; //bis
        BufferedOutputStream BuffOutput; //bos
        
        int ValorNum; //in
        byte[] ArrayBytesServer; //byteArray
        
        //Fichero a transferir
                     //filename        '
        final String LlamadaArchivo = "//../home/gersson/"; 
        
      
        try
        {
            try
            {
              
                Sck_Server = new ServerSocket(5000);
                Sck = Sck_Server.accept();
                           //localFile
             
              ValorEntrada = new DataInputStream(Sck.getInputStream());
              
              String nombre = ValorEntrada.readUTF();
                           
              System.out.println(nombre);
              
                           
                final File DirectorioDeArchi = new File(LlamadaArchivo+nombre);          
                       //client
                //Socket Sck_Client = new Socket("localhost", 5000);
                
                BuffInput = new BufferedInputStream(new FileInputStream(DirectorioDeArchi));
                BuffOutput = new BufferedOutputStream(Sck.getOutputStream());
                
                //Enviamos el nombre del fichero
                                //dos
                DataOutputStream DatOutStm = new DataOutputStream(Sck.getOutputStream());
                DatOutStm.writeUTF(DirectorioDeArchi.getName());
                
                //Enviamos el fichero
                ArrayBytesServer = new byte[8192];
            
                  while ((ValorNum = BuffInput.read(ArrayBytesServer)) != -1)
                  {
                    BuffOutput.write(ArrayBytesServer, 0, ValorNum);
                  }

                
                  BuffInput.close();
                  BuffOutput.close();

            }//Cierre del try interno
            catch(Exception ValorExcepcion)
            {
                System.err.println(ValorExcepcion);
            
            }//Cierre del catch interno
        
        }//Cierre del try principal
        catch(Exception ValorExcepcion)
        {
            System.err.println(ValorExcepcion);
            
        }//Cierre del catch principal
        
        
    }
 
}
