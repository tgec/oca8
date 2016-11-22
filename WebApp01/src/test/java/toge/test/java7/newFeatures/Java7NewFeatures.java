package toge.test.java7.newFeatures;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java7.model.Car;
import java7.staticMethod.TestData;
import static java.nio.file.StandardWatchEventKinds.*;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "classpath:config/test-config.xml")
public class Java7NewFeatures {

	@Test
	public void test(){
		System.out.println("test java 7 new features");
	}

	@Test
	public void diamondOperator(){
//		DIAMOND OPERATOR
		
//		before
		Map<Integer, List<Car>> carMap01 = new HashMap<Integer, List<Car>>();
			carMap01.put(1, TestData.fillListOfCar());
		
			for( Entry<Integer, List<Car>> entry : carMap01.entrySet() ) {
				System.out.println( "Code:" + entry.getKey() );
				List<Car> v = entry.getValue();
				for( Car c:v ) {
					System.out.println( "Car name: " + c.getName() + " , engine: " + c.getEngine() + ", year: " + c.getBeginYearOfProduction());
				}
			}

//		NEW	
		Map<Integer, List<Car>> carMap02 = new HashMap<>();
			carMap02.put(1, TestData.fillListOfCar());
	
	}

	@Test
	public void stringsInSwitchStatements(){
//		STRINGS IN SWITCH STATEMENTS
		
//		before
		List<Car> listOfCar = TestData.fillListOfCar();
		Car selectedCar = listOfCar.get(0);
		switch (selectedCar.getDoors()) {
		case 3:
			System.out.println("Selected car: " + selectedCar.getName() + " has 3 dors." );
			break;
		case 5:
			System.out.println("Selected car: " + selectedCar.getName() + " has 5 dors." );
			break;

		default:
			System.out.println("There is no record for this car!!!" );
			break;
		}
		
//		NEW	
		switch (selectedCar.getName()) {
		case "AUDI":
			System.out.println("Selected car is AUDI" );
			break;
		case "BMW":
			System.out.println("Selected car is BMW" );
			break;
		default:
			System.out.println("No match" );
			break;
		}
		
	}

	@Test
	public void automaticResourceManagement(){
//		AUTOMATIC RESOURCE MANAGEMENT
		
//		before
//		Resources such as Connections, Files, Input/OutStreams, etc. should be closed manually by the developer by writing bog-standard code. 
//		Usually we use a try-finally block to close the respective resources.
		
		FileOutputStream fos = null;
		DataOutputStream dos = null;
		try{

			fos = new FileOutputStream("cars.txt");
			dos = new DataOutputStream(fos);
            dos.writeUTF("Resources such as Connections, Files, Input/OutStreams, etc. should be closed manually by the developer by writing bog-standard code. ");

      } catch(IOException e) {
            e.printStackTrace();
      } finally{
            try{
                  fos.close();
                  dos.close();
            } catch(IOException e) {
            	e.printStackTrace();
            }
      }
		
//		NEW	
//		Java 7 has introduced feature to manage the resources automatically. It is simple in operation, too. 
//		All we have to do is declare the resources in the try:
		
		try(FileOutputStream fos2 = new FileOutputStream("cars2.txt");
			DataOutputStream dos2 = new DataOutputStream(fos2)) {
				dos2.writeUTF(	"Java 7 has introduced feature to manage the resources automatically. It is simple in operation, too. "
							+ 	"All we have to do is declare the resources in the try:");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void numericLiteralsWithUnderscores(){
//		NUMERIC LITERALS WITH UNDERSCORES
		
//		before
		int thousand = 1000;
		System.out.println(thousand);
		
//		NEW	
//		Java 7 introduced underscores in identifying the places. For example, you can declare 1000 as shown below:
		thousand = 1_000;
		System.out.println(thousand);
		int million = 1_000_000;
		System.out.println(million);
	}

	@Test
	public void improvedExceptionHandling(){
//		IMPROVED EXCEPTION HANDLING - MULTI-CATCH FUNCTIONALITY
		
//		before
//		Let’s say you have a method that throws three exceptions. In the current state, you would deal them individually as shown in below:
		try{
//      	method();
		} catch(IndexOutOfBoundsException e) {
            // log and deal with ExceptionOne
		} catch(NumberFormatException e) {
            // log and deal with ExceptionTwo
		} catch(NullPointerException e) {
            // log and deal with ExceptionThree
		}	
		
//		NEW	
		try{
//      	method();
		} catch(IndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            // log and deal with all Exceptions
		}
		
	}

	@Test
	public void newFileSystemAPI_WorkingWithPath(){
		
//		NEW FILE SYSTEM API (NIO 2.0) - Working with Path
//		NEW	
		Path path= Paths.get("D:\\JAVA\\Eclipse Neon Java 8\\projectsEclipseNEON");
		
			System.out.println("Number of Nodes:"	+ path.getNameCount());
            System.out.println("File Name:"			+ path.getFileName());
            System.out.println("File Root:"			+ path.getRoot());
            System.out.println("File Parent:"		+ path.getParent());
            System.out.println("File Parent:"		+ path.getFileSystem());
            System.out.println("File Parent:"		+ path.getName(0));
		
	}

	@Test
	public void newFileSystemAPI_FileChangeNotifications(){
		
//		FILE CHANGE NOTIFICATIONS
//		NEW	
//		The WatchService API lets you receive notification events upon changes to the subject (directory or file).

		Path path = Paths.get("D:\\JAVA\\Eclipse Neon Java 8\\projectsEclipseNEON");
		WatchService watchService = null;
			try {
				watchService = FileSystems.getDefault().newWatchService();
				path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			WatchKey key = null;
			
			while(true) {
				try {
					key = watchService.take();
					for (WatchEvent<?> event : key.pollEvents()) {
						Kind<?> kind = event.kind();
						System.out.println("Event on " + event.context().toString() + " is " + kind);
					}
				} catch (InterruptedException e) {
					System.out.println("InterruptedException: "+e.getMessage());
				}
				boolean reset = key.reset();
				if(!reset)
					break;
			}
		
	}

	@Test
	public void processorTest() throws InterruptedException{
		
//		PROCESSOR TEST
		int brojac = 0;
		for (int i = 0; i < 5; i++) {
			Thread.sleep(1000);
			System.out.println("Iteration: " + brojac);
			System.out.println("Number od available processors: " + Runtime.getRuntime().availableProcessors());
			System.out.println("Free memory: " + Runtime.getRuntime().freeMemory());
			System.out.println("Max memory: " + Runtime.getRuntime().maxMemory());
			System.out.println("Total memory: " + Runtime.getRuntime().totalMemory());
			System.out.println("---");
			brojac++;
		}
	}
	
} 