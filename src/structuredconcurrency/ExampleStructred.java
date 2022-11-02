package structuredconcurrency;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ExampleStructred {

    public static void main(String a[]){
        ExampleStructred  object = new ExampleStructred();
        try {
            System.out.println(object.getDog());
        }catch (Exception e){
            System.out.println("exception"+ e);
        }
    }

   public String getDog() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            Future<String> name  = scope.fork(this::getName);
            Future<String> breed = scope.fork(this::dogBreed);

            scope.join();
            scope.throwIfFailed();

            return "it's name is:" + name.resultNow() + ", and is a " + breed.resultNow();
        }
    }

    String getName(){return "Max";}

    String dogBreed(){return "Golden retriever";}
}
