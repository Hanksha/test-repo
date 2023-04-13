{
    "type": "template",
    "comments": "This Gloop template can be accessed via a browser at `http://<host>:<port>/app/examples/form`. \n\nIt demonstrates how to post data using an HTML form. The form submits a customer record to the Gloop template exposed at `http://<host>:<port>/app/examples/customers` which adds it to the database and display the customers in a table.",
    "paths": [
        "/examples/form"
    ],
    "methods": [
        "GET"
    ],
    "steps": [
        {
            "type": "invokeTemplate",
            "comments": "include the `template.util.Head` template",
            "templateName": "template.util.Head"
        },
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "containerTag",
                    "tagName": "p",
                    "content": "Customer Form"
                },
                {
                    "type": "containerTag",
                    "comments": "This customer form will submit its data to the Gloop template exposed at `/app/examples/customers` with the `POST` HTTP method.\n\nNote that Gloop SQL services automatically prevent SQL injection from forms.",
                    "children": [
                        {
                            "type": "containerTag",
                            "tagName": "label",
                            "content": "First Name:"
                        },
                        {
                            "type": "containerTag",
                            "tagName": "input",
                            "attributes": {
                                "type": "text",
                                "name": "firstName",
                                "value": "John"
                            }
                        },
                        {
                            "type": "containerTag",
                            "tagName": "br"
                        },
                        {
                            "type": "containerTag",
                            "tagName": "label",
                            "content": "Last Name:"
                        },
                        {
                            "type": "containerTag",
                            "tagName": "input",
                            "attributes": {
                                "type": "text",
                                "name": "lastName",
                                "value": "Doe"
                            }
                        },
                        {
                            "type": "containerTag",
                            "tagName": "br"
                        },
                        {
                            "type": "containerTag",
                            "tagName": "label",
                            "content": "Email:"
                        },
                        {
                            "type": "containerTag",
                            "tagName": "input",
                            "attributes": {
                                "type": "email",
                                "name": "email",
                                "value": "john.doe@test.com"
                            }
                        },
                        {
                            "type": "containerTag",
                            "tagName": "br"
                        },
                        {
                            "type": "containerTag",
                            "tagName": "input",
                            "attributes": {
                                "type": "submit",
                                "value": "Submit"
                            }
                        }
                    ],
                    "tagName": "form",
                    "attributes": {
                        "action": "/app/examples/customers",
                        "method": "post"
                    }
                }
            ],
            "tagName": "body"
        }
    ]
}