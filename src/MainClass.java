

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MainClass 
{
    FileInputStream FIS;
    BufferedInputStream BIS;
    
    public Player player;
    
    public long pauselocation;
    public long songtotallength;
    public String fileloction;
    
    public void Stop()
    {
        if(player != null)
        {
            player.close();
            
            pauselocation = 0;
            songtotallength = 0;
        }
    }
    
    public void Pause()
    {
        if(player != null)
        {
            try 
            {
                pauselocation = FIS.available();
                player.close();
            } 
            catch (IOException ex) 
            {
                
            }
        }
    }
    
    public void Resume()
    {
        try 
        {
            FIS = new FileInputStream(fileloction);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            FIS.skip(songtotallength - pauselocation);
        } 
        catch (FileNotFoundException | JavaLayerException ex) 
        {
            
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
            @Override
            public void run()
            {
                try 
                {
                    player.play();
                } 
                catch (JavaLayerException ex) {
                    
                }
            }
        }.start();
        
    }
    
    public void Play(String path)
    {
        try 
        {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            
            songtotallength = FIS.available();
            fileloction = path + "";
        } 
        catch (FileNotFoundException | JavaLayerException ex) 
        {
            
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new Thread()
        {
            @Override
            public void run()
            {
                try 
                {
                    player.play();
                } 
                catch (JavaLayerException ex) {
                    
                }
            }
        }.start();
        
    }
    
}
