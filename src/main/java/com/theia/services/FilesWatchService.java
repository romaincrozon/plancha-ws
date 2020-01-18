package com.theia.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * Surveiller les changements dans un dossier
 */
public class FilesWatchService implements Runnable {
 
    private Path path = null;
 
    /**
     * Constructor
     * @param pathname String dossier à surveiller
     */
    public FilesWatchService(String pathname) {
        this.path = Paths.get(pathname);
        System.out.println("Start Watch Service on:" + pathname);
    }
 
    /**
     * Worker
     */
    public void run() {
        WatchService watchService = null;
        try {
            watchService = this.path.getFileSystem().newWatchService();
            //Enregistrer les opérations à surveiller
            this.path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);
 
            WatchKey watchKey;
            // boucle sans fin
            while (!Thread.interrupted()) {
                watchKey = watchService.take();
 
                //traiter les evenements
                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    String fileName = event.context().toString();
                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                        System.out.println("new file create "+fileName);
                    } else if (StandardWatchEventKinds.ENTRY_MODIFY.equals(event.kind())) {
                        System.out.println(fileName+" has been modified");
                    } else if (StandardWatchEventKinds.ENTRY_DELETE.equals(event.kind())) {
                        System.out.println(fileName+" has been deleted");
                    } else if (StandardWatchEventKinds.OVERFLOW.equals(event.kind())) {
                        System.out.println("Strange event");
                        continue;
                    }
                }
                //se place en attente de message
                watchKey.reset();
            }
        } catch (InterruptedException ex) {
            try {
                if (watchService!=null) watchService.close();
                System.out.println("Stop FolderSpy");
            } catch (IOException ex1) {
                Logger.getLogger(FilesWatchService.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Exception ex) {
            Logger.getLogger(FilesWatchService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}