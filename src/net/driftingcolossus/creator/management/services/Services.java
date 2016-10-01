package net.driftingcolossus.creator.management.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import net.driftingcolossus.creator.graphics.gui.splash.SplashScreen;

public final class Services {
	
	private static final Class<?>[] CLASSES_SERVICES = new Class<?>[]{
		
	};
	private static final Class<?>[] CLASSES_IO = new Class<?>[]{
		
	};
	private static final Class<?>[] CLASSES_GUIS = new Class<?>[]{
		
	};
	private static final Class<?>[] CLASSES_PLUGINS = new Class<?>[]{
		
	};
	private static final Class<?>[] CLASSES_UPDATE = new Class<?>[]{
		
	};
	private static final Class<?>[] CLASSES_UTILS = new Class<?>[]{
		LogService.class
	};
	
	private static final Class<?>[][] CLASSES = new Class<?>[][]{
		CLASSES_SERVICES,
		CLASSES_IO,
		CLASSES_GUIS,
		CLASSES_PLUGINS,
		CLASSES_UPDATE,
		CLASSES_UTILS
	};
	
	
	
	private static ResourceFinder finder_instance;
	
	private static Thread thread_services;

	public static final void find(){
		finder_instance = new ResourceFinder("META-INF/services/");
		thread_services = newSearchThread();
		thread_services.start();
		
	}
	
	public static void complete(){
		while(thread_services.isAlive()){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static final Thread newSearchThread(){
		Thread thread = new Thread("Loader"){

			@Override
			public void run() {
				ArrayList<Class<?>> services = new ArrayList<Class<?>>();
				ArrayList<Class<?>> io = new ArrayList<Class<?>>();
				ArrayList<Class<?>> guis = new ArrayList<Class<?>>();
				ArrayList<Class<?>> plugins = new ArrayList<Class<?>>();
				ArrayList<Class<?>> updates = new ArrayList<Class<?>>();
				final ArrayList<Class<?>> utils = new ArrayList<Class<?>>();
				
				for(Class<?>[] arr : CLASSES){
					
					for(Class<?> cl  : arr){
						try {
							List<Class<?>> list = finder_instance.findAllImplementations(cl);
							ArrayList<Class<?>> parent = null;
							if(arr.equals(CLASSES_SERVICES)){parent = services;}
							if(arr.equals(CLASSES_IO)){parent = io;}
							if(arr.equals(CLASSES_GUIS)){parent = guis;}
							if(arr.equals(CLASSES_PLUGINS)){parent = plugins;}
							if(arr.equals(CLASSES_UPDATE)){parent = updates;}
							if(arr.equals(CLASSES_UTILS)){parent = utils;}
							if(list != null){
								
							}
							parent.addAll(list);
							
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}
				
				Runnable run = new Runnable(){

					@Override
					public void run() {
						SplashScreen.getInstance().setUtilsAmount(0, utils.size());
					}
					
				};
				Platform.runLater(run);
				
			}
			
		};
		return thread;
	}
	
	
	

}
