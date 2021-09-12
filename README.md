# Layered architecture With Pipe & Filters example using Sping.io

Each layer of the layered architecture pattern has a specific role and responsibility within the application. In this example the application is divided in 3 layers, Controllers, Services and Data. A vertical layer was also built, called CrossCutting, which has functionality that can be accessed by all layers, like PipeLines, Constanst and more.

For this example Pipes and Filters architecture was used to filter and validate incoming data from the controller before it is inserted or updated into the database, thus containing two filters, Data Validation and Object Serializer. To ensure that processes do not get stuck inside the pipeline, Multithreading was used on all pipes and filters, in order to guarantee faster processing and that no processes are blocked or waiting for others

![alt text](https://github.com/RicardoJardim/Pipes-Filters/blob/main/image.png "Diagram")

## Layered architecture

One of the powerful features of the layered architecture pattern is the separation of concerns among components. Components within a specific layer deal only with logic that pertains to that layer. For example, components in the data layer deal only with data logic, whereas components residing in the business layer deal only with business logic. This type of component classification makes it easy to build effective roles and responsibility models into your architecture, and also makes it easy to develop, test, govern, and maintain applications using this architecture pattern due to well-defined component interfaces and limited component scope.
Controllers

- Comunication with the outside world
- Authentication and Authorization

Services

- Interconnection between the Data Layer and Controllers
- Depency Injection into Controllers
- PipeLine execution

Data

- Interconnection between the Services and Database
- Factories for creating objects and Entities
- Repositories to communicate with the database

CrossCutting

- Http Decorator for erro handling and responses
- Constants
- PipeLines, for validating objects and Entities

## Pipes & Filters

Pipe and Filter is another architectural pattern, which has independent entities called filters (components) which perform transformations on data and process the input they receive, and pipes, which serve as connectors for the stream of data being transformed, each connected to the next component in the pipeline. Filters can work asynchronously. Thefinal output is given to the consumer, known as a sink. The architectural pattern is very popular and used in many systems, such as the text-based utilities in the UNIX operating system, compileres and data transformations.

The biggest advantage is that a pipe has a single source for its input and a single target for its output. It preserves the sequence of data items, and it does not alter the data passing through, ensuring loose and flexible coupling of components.
