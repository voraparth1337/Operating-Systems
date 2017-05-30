# Operating-Systems

This repo contains implementation code for various concepts of Operating System

List: 
* Disk Scheduling (Java)

    In operating systems, seek time is very important. Since all device requests are linked in queues, the seek time is increased causing the system to slow down. Disk Scheduling Algorithms are used to reduce the total seek time of any request.
Although there are other algorithms that reduce the seek time of all requests, I will only concentrate on the following disk scheduling algorithms:
[1) First Come-First Serve (FCFS)](https://www.youtube.com/watch?v=X63lwwQtpic)
[2) Shortest Seek Time First (SSTF)](https://www.youtube.com/watch?v=X63lwwQtpic)
[3) Elevator (SCAN)](https://www.youtube.com/watch?v=3wwadNI2OMc&t=273s)
These algorithms are not hard to understand, but they can confuse someone because they are so similar. What we are striving for by using these algorithms is keeping Head Movements (# tracks) to the least amount as possible. The less the head has to move the faster the seek time will be.
The other three
[4) Circular SCAN (C-SCAN)](https://www.youtube.com/watch?v=3wwadNI2OMc&t=273s)
[5) LOOK](https://www.youtube.com/watch?v=junVVYqF4ms)
[6) C-LOOK](https://www.youtube.com/watch?v=junVVYqF4ms)
can be very easily derived from the SCAN code.
I have included the tutorial video links.

* Dining Philosophers Problem using Threads (Java)

* File Operations (Java)
Includes various file operations in Java
I have tried to create seperate fuction for each file operation, you can easily extract them from the code and use it in your code
Output file might give you a better idea about the functioning of the code
Warning: This code will actually permanently delete files from your file system so be careful about what you do :)

* Process Scheduling (Java)
  * Non Pre emptive algorithms
    1. FCFS - First come first served
    2. SJF - Shortest Job First
    3. Prioriy based

  * Pre emptive algorithms
    1. SRT- Shortest remaining time
    2. Round Robin
    3. Prioriy based
    
All necessary explanation is included with the comments in the code at appropriate locations
I have tried to keep all of these codes very consistent with one another, changing only 1-2 lines of code you can refactor your code to perform other algorithms.

* Producer - Consumer Problem
    In computing, the producer–consumer problem[1][2] (also known as the bounded-buffer problem) is a classic example of a multi-process synchronization problem. The problem describes two processes, the producer and the consumer, who share a common, fixed-size buffer used as a queue. The producer's job is to generate data, put it into the buffer, and start again. At the same time, the consumer is consuming the data (i.e., removing it from the buffer), one piece at a time. The problem is to make sure that the producer won't try to add data into the buffer if it's full and that the consumer won't try to remove data from an empty buffer.
The solution for the producer is to either go to sleep or discard data if the buffer is full. The next time the consumer removes an item from the buffer, it notifies the producer, who starts to fill the buffer again. In the same way, the consumer can go to sleep if it finds the buffer to be empty. The next time the producer puts data into the buffer, it wakes up the sleeping consumer. The solution can be reached by means of inter-process communication, typically using semaphores. An inadequate solution could result in a deadlock where both processes are waiting to be awakened. The problem can also be generalized to have multiple producers and consumers.
Source - Wikipedia
I have included three different approaches to solving this problem
1. Using semaphores (Java has an in-built semaphore class)
2. Using message passing
3. Using monitor (Monitors in java are implemented using Synchronized blocks)
4. 
P.S 
Change the main class name or change the java file name or the code wont work

GLHF
