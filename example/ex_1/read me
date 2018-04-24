
### 一.CountDownLatch用法  

- 可以实现类似计数器的功能。比如有一个任务A，它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
- 不可以重复使用。
```
    //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
    public void await() throws InterruptedException { };     
    
    //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };    
    
    //将count值减1  
    public void countDown() { };  
```

### 二.CyclicBarrier用法

- 允许一组线程全部等待彼此达到共同屏障点的同步辅助。循环阻塞在涉及固定大小的线程方的程序中很有用，这些线程必须等待彼此。
- 屏障被称为循环 ，因为它可以在等待的线程被释放之后重新使用。

```
    //用来挂起当前线程，直至所有线程都到达barrier状态再同时执行后续任务
    public int await() throws InterruptedException, BrokenBarrierException { };
    
    //让这些线程等待至一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务
    public int await(long timeout, TimeUnit unit)throws InterruptedException,BrokenBarrierException,TimeoutException { };
```

### 三.Semaphore用法
- 翻译成字面意思为信号量，Semaphore可以控同时访问的线程个数，通过acquire()获取一个许可，如果没有就等待，而release()释放一个许可。
- 构造方法
```
    //参数permits表示许可数目，即同时可以允许多少线程进行访问
    public Semaphore(int permits) {          
        sync = new NonfairSync(permits);
    }
    
    //这个多了一个参数fair表示是否是公平的，即等待时间越久的越先获取许可
    public Semaphore(int permits, boolean fair) {    
        sync = (fair)? new FairSync(permits) : new NonfairSync(permits);
    }
```
- 成员方法
    + acquire()用来获取一个许可，若无许可能够获得，则会一直等待，直到获得许可。
    + release()用来释放许可。注意，在释放许可之前，必须先获获得许可。
   
        
```
    //获取一个许可
    public void acquire() throws InterruptedException {  }      
    
    //获取permits个许可
    public void acquire(int permits) throws InterruptedException { }    
    
    //释放一个许可
    public void release() { }          
    
    //释放permits个许可
    public void release(int permits) { }    
```
    以上4个方法都会被阻塞。一下4个会立马返回结果。
```
    //尝试获取一个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
    public boolean tryAcquire() { };    
   
   //尝试获取一个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
    public boolean tryAcquire(long timeout, TimeUnit unit) throws InterruptedException { };  
   
   //尝试获取permits个许可，若获取成功，则立即返回true，若获取失败，则立即返回false
    public boolean tryAcquire(int permits) { }; 
   
   //尝试获取permits个许可，若在指定的时间内获取成功，则立即返回true，否则则立即返回false
    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) throws InterruptedException { }; 
```
    获取可用的许可数目
```
    //获取可用的许可数目
    public int availablePermits() {
            return sync.getPermits();
    }
```

### 四.总结
  1. CountDownLatch和CyclicBarrier都能够实现线程之间的等待，只不过它们侧重点不同：
      * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
      * 而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
      * 另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。

  2. Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。