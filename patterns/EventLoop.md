# The Diagram

![EventLoop Diagram](https://blog-image-1258275666.cos.ap-chengdu.myqcloud.com/EventLoop-Diagram.png)

- An EventLoopGroup contains one or more EventLoops.
- An EventLoop is bound to a single Thread for its lifetime.
- All I/O events processed by an EventLoop are handled on its dedicated Thread.
- A Channel is registered for its lifetime with a single EventLoop.
- A single EventLoop may be assigned to one or more Channels.


![2 EventLoop Groups](https://blog-image-1258275666.cos.ap-chengdu.myqcloud.com/Server-2-EventLoopGroup.png)
