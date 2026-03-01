how you can make a thread sleep
for making thread sleep we can use sleep method, here We need to use Thread class and sleep method
sleep method will throw checked exception and we need to use try catch block

while coming to the constructor when we created parameterized constructor bascially default constructor will
go away . but when i used noargsconstructor and allargsconstructor with lombok it is working fine .how it is possible

basically for us lombok will create the both constructor at compile time and provide to us

-> how you can create the intstream to get the numbers between 1 to 10
with help of IntStream class we can create . we have rangeclosed which will take start number and end number
and will increment by 1 and provide stream.

-> when i want to map anyStream to ObjectStream then i can use mapToObj method

-> if i want to create Flux of IntStream i can use Flux.range method it will take two params
first one is start and second one is count 
for example if i given 1 and 10 it will create streams it will start from 1 and will increment upto 10 iterations
output is 1,2,3,4,5,6,7,8,9,10 

MediaType.TEXT_EVENT_STREAM_VALUE will help us share the output as a stream
like what ever it get from the downsteam or method it will immediately will given to the consumer

basically when we are using just IntStream for the return list the method is synchronous and it will block main thread to complete
the execution

but when we used Flux.range it is asynchronous and main thread will not block and a new thread from reactive
pool will take care of execution asynchronously. and the reactive thread will the response by one by one 
in streaming level

2------
