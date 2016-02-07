# Rest Doodle

This is an example of a REST API in Spring Boot

It allows a potential user to create, edit, list, read fictive companies, as well as add an unlimited amount of beneficial owners to a company.
It uses hsqldb for in memory storage, since this is more an exercise than anything, but you can change these settings and point it to your favorite DB in the `application.properties`

The application boots on **http://localhost:4950**
The main endpoints are: 
**/v1/companies**
**/v1/companies/{companyId}/beneficial_owners**

## Usage

To run the application please use the following command line:
`mvn spring-boot:run` 

To obtain a list of all the companies:
```
curl -i -X GET -H "Content-Type: application/json" "http://localhost:4950/v1/companies"
```

Returns the following JSON object:
```
{
    "httpStatus":200,
    "message":"OK",
    "companies":[
        {
            "companyId":"f0355cfb-e4f2-471c-9464-6ad3a4f38d8b",
            "name":"A Name"
        }
    ]
}
```

To create a new company:
```
curl -i -X POST -H "Content-Type: application/json" -d '{"name":"A Name","address":"Address stuff","city":"Littleport","country":"UK","email":"hey@stuff.org","phoneNumber":"+44something-else","employees":[{"firstName":"Anders","lastName":"Kjolholm"},{"firstName":"Michael","lastName":"Poulsen"}]}' "http://localhost:4950/v1/companies"
```

Returns the following JSON object:
```
{
    "httpStatus":201,
    "message":"Created",
    "company":
    {
        "companyId":"f0355cfb-e4f2-471c-9464-6ad3a4f38d8b",
        "name":"A Name",
        "address":"Address stuff",
        "city":"Littleport",
        "country":"UK",
        "email":"hey@stuff.org",
        "phoneNumber":"+44something-else",
        "employees":[
            {
                "firstName":"Anders",
                "lastName":"Kjolholm"
            },
            {
                "firstName":"Michael",
                "lastName":"Poulsen"
            }
        ]
    }
}
```

To get the details of an existing company:
```
curl -i -X GET -H "Content-Type: application/json" "http://localhost:4950/v1/companies/f0355cfb-e4f2-471c-9464-6ad3a4f38d8b"
```

Returns the following JSON object:
```
{
    "httpStatus":200,
    "message":"OK",
    "company":
    {
        "companyId":"f0355cfb-e4f2-471c-9464-6ad3a4f38d8b",
        "name":"A Name",
        "address":"Address stuff",
        "city":"Littleport",
        "country":"UK",
        "email":"hey@stuff.org",
        "phoneNumber":"+44something-else",
        "employees":[
            {
                "firstName":"Anders",
                "lastName":"Kjolholm"
            },
            {
                "firstName":"Michael",
                "lastName":"Poulsen"
            }
        ]
    }
}
```

To update the details of an existing company:
```
curl -i -X PUT -H "Content-Type: application/json" -d '{"name":"OTHER Name","address":"Address stuff","city":"Littleport","country":"UK","email":"hey@stuff.org","phoneNumber":"+44something-else","employees":[{"firstName":"Anders","lastName":"Kjolholm"},{"firstName":"Michael","lastName":"Poulsen"}]}' "http://localhost:4950/v1/companies/f0355cfb-e4f2-471c-9464-6ad3a4f38d8b"
```

Returns the following JSON object:
```
{
    "httpStatus":200,
    "message":"OK",
    "company":
    {
        "companyId":"f0355cfb-e4f2-471c-9464-6ad3a4f38d8b",
        "name":"OTHER Name",
        "address":"Address stuff",
        "city":"Littleport",
        "country":"UK",
        "email":"hey@stuff.org",
        "phoneNumber":"+44something-else",
        "employees":[
            {
                "firstName":"Anders",
                "lastName":"Kjolholm"
            },
            {
                "firstName":"Michael",
                "lastName":"Poulsen"
            }
        ]
    }
}
```

To add beneficial owners to an existing company:
```
curl -i -X POST -H "Content-Type: application/json" -d '{"firstName":"Bob","lastName":"Doe"}' "http://localhost:4950/v1/companies/f0355cfb-e4f2-471c-9464-6ad3a4f38d8b/beneficial_owners"
```

Returns the following JSON object:
```
{
    "httpStatus":201,
    "message":"Created",
    "beneficialOwner":
    {
        "beneficialOwnerId":"24a916ce-0ba0-42ec-b920-1afb3edbadea",
        "firstName":"Bob",
        "lastName":"Doe"
    }
}
```
## JavaScript Client
All the CURL operations can also be performed from the JS Client:
**http://localhost:4950**
