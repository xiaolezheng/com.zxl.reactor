import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class ThreadPool {
	private BlockingQueue<Runnable> taskQueue = null;
	private AtomicBoolean isStop = new AtomicBoolean(false);
	private List<PoolThread> pool = new ArrayList<PoolThread>();
	public ThreadPool(int poolSize,int taskQueueSize){
		this.taskQueue = new ArrayBlockingQueue<Runnable>(taskQueueSize);
		for(int i=0;i<poolSize;i++){
			pool.add(new PoolThread(this.taskQueue));
		}
		for(PoolThread thread: pool){
			thread.start();
		}
	}
	
	public synchronized void execute(Runnable task){
		try {
			this.taskQueue.put(task);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop(){
		this.isStop.getAndSet(true);
		for(PoolThread thread: pool){
			thread.doStop();
		}
	}
	
	
	
    static class PoolThread extends Thread{
    	private AtomicBoolean isStop = new AtomicBoolean(false);
    	private BlockingQueue<Runnable> taskQueue = null;
    	public PoolThread(BlockingQueue<Runnable> queue){
    		this.taskQueue = queue;
    	}
    	public void run(){
    		while(!isStop.get()){
    			try{
    				System.out.println("cur.thread.id: "+this.getId());
    				Runnable run = this.taskQueue.take();
    				run.run();
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    		}
    	}
    	public void doStop(){
    		this.isStop.getAndSet(true);
    		//this.interrupt();
    	}
    }
    
    public static void main(String[] args){
    	long start = System.currentTimeMillis();
    	ThreadPool pool = new ThreadPool(3,5);
    
        for(int i=0;i<20;i++){
        	Runnable task = new Runnable(){
        		public void run(){
        			System.out.println("task:"+new Random().nextInt());
        			try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        		}
        	};
        	pool.execute(task);
        }
        try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        pool.stop();
        long time = System.currentTimeMillis() - start;
        System.out.println("time: "+time);
    }
}



