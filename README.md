# Restful Web Service and Client with Spring Boot

Spring Starter Web ,project lombok , oracle-jdbc-connector

## Our pretty scenario


 - We have Rest Service API that exposes messages stored in database 
- Messages can have 2 possible of states : SENT(1),NOT_SENT(0)
- Service is responsible to expose API to clients in order their consuming messages
- After Client consumed messages , that messages`s status need to be changed 
- Service make change state of messages after make sure that client has recevied that message
- To accomplish Data Safety transmission ,  we used 'acknowledge' concept which is just about client sending acknowledgement about receving message to server ,
and server will change status of messages from NOT_SENT into SENT when got client`s acknowledgement . 
- in order to load network traffic less , we are sending only token(that`s random string) , not whole messages , not their ids .
- Server sends messages (that`re were not sent yet) along with token(namely, acknowledgement) .
and store that acknowledgement on the server side .(you can also store in database)
- when client sent token(or acknowledgment ) to the server , server checks it , and he will be  sure that client got whole messages that server sent along with that acknowledgement .





## Installation


```bash
git clone [url]
mvn spring-boot:run
```
   _

>  To notify server so that client has received messages succesfully 
>    include  the folowing {header : value } information into each request
>    acknowledgement : your_token 

_
   

```
 - GET - Getting resource     -- /api/messages 
 - PUT - Updating resource -- api/messages 
```


```
{
    "messageDTO": [
        {
            "id": 1,
            "msg": "qqqq .",
            "createdAt": "2020-02-28"
        },
        {
            "id": 2,
            "msg": "AAAAA.",
            "createdAt": "2020-02-28"
        },
        {
            "id": 3,
            "msg": "bbbb .",
            "createdAt": "2020-02-28"
        },
        {
            "id": 4,
            "msg": "cccc .",
            "createdAt": "2020-02-28"
        }
    ],
    "acknowledgement": "43750316-cdf1-4af8-89a8-732b311cca89Fri Feb 28 22:54:32 AZT 2020"
}
```




## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
