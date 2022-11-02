package structuredconcurrency;

import jdk.incubator.concurrent.StructuredTaskScope;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class InvokeAnyPatternWithStructuredConcurrency {

    public static void main(String a[]){
        InvokeAnyPatternWithStructuredConcurrency  object = new InvokeAnyPatternWithStructuredConcurrency();
        try {
            System.out.println(object.getDog());
        }catch (Exception e){
            System.out.println("exception"+ e);
        }
    }

    String getDog() throws ExecutionException, InterruptedException {
        try (var scope = new StructuredTaskScope.ShutdownOnSuccess<String>()) {
            scope.fork(this::getName);
            scope.fork(this::dogBreed);

            scope.join();

            return "result: " + scope.result();
        }
    }

    String getName() throws InterruptedException {
        Thread.sleep(5000);
        return "Max";
    }

    String dogBreed(){return "Golden retriever";}
}
