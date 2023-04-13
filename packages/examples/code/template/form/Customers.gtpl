{
    "type": "template",
    "comments": "This Gloop template can be accessed via a browser at `http://<host>:<port>/app/examples/customers`. \n\nIt demonstrates how to retrieve data from a database and display them in a table format. \n\nIt also demonstrates how data can be received from a HTML form with HTTP method `POST` and inserted into a database.",
    "input": [
        {
            "name": "customer",
            "type": "model",
            "gloopProperties": [
                {
                    "name": "firstName",
                    "defaultValue": ""
                },
                {
                    "name": "lastName"
                },
                {
                    "name": "email"
                }
            ]
        }
    ],
    "bodyParameter": "customer",
    "paths": [
        "/examples/customers"
    ],
    "methods": [
        "POST",
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
                    "type": "fork",
                    "comments": "check if a customer should be added",
                    "expression": "customer != null && customer.firstName",
                    "children": [
                        {
                            "type": "block",
                            "label": "true",
                            "blockType": "TRY_CATCH",
                            "children": [
                                {
                                    "type": "invokeGloop",
                                    "comments": "add the customer",
                                    "name": "template.form.AddCustomer",
                                    "inputs": [
                                        {
                                            "from": [
                                                "customer"
                                            ],
                                            "to": [
                                                "customer"
                                            ]
                                        }
                                    ]
                                },
                                {
                                    "type": "containerTag",
                                    "comments": "show the customer added success alert",
                                    "tagName": "div",
                                    "attributes": {
                                        "class": "alert success"
                                    },
                                    "content": "\"Customer '${customer.firstName} ${customer.lastName}' with email '${customer.email}' added!\"",
                                    "evaluateContent": true
                                }
                            ],
                            "catch": [
                                {
                                    "type": "containerTag",
                                    "comments": "if there is any error, show the error alert with the exception message",
                                    "tagName": "div",
                                    "attributes": {
                                        "class": "alert error"
                                    },
                                    "content": "\"Failed to add customer: ${$gloopException.message}\"",
                                    "evaluateContent": true
                                }
                            ]
                        }
                    ]
                },
                {
                    "type": "invokeGloop",
                    "comments": "retrieve all the customers",
                    "name": "template.form.GetCustomers",
                    "declare": [
                        {
                            "variables": [
                                {
                                    "name": "customers",
                                    "type": "model",
                                    "array": true,
                                    "reference": "template.form.Customer"
                                }
                            ]
                        }
                    ],
                    "outputs": [
                        {
                            "from": [
                                "customers"
                            ],
                            "to": [
                                "customers"
                            ]
                        }
                    ]
                },
                {
                    "type": "containerTag",
                    "comments": "The customer table that displays all the customers.\n\nNote that this table can be generated using the content assist and the \"table from model array\" proposal.",
                    "children": [
                        {
                            "type": "containerTag",
                            "children": [
                                {
                                    "type": "containerTag",
                                    "tagName": "th",
                                    "content": "First Name"
                                },
                                {
                                    "type": "containerTag",
                                    "tagName": "th",
                                    "content": "Last Name"
                                },
                                {
                                    "type": "containerTag",
                                    "tagName": "th",
                                    "content": "Email"
                                }
                            ],
                            "tagName": "tr"
                        },
                        {
                            "type": "iterate",
                            "comments": "iterate over the customers to generate the table row",
                            "input": {
                                "path": [
                                    "customers"
                                ]
                            },
                            "children": [
                                {
                                    "type": "containerTag",
                                    "children": [
                                        {
                                            "type": "containerTag",
                                            "comments": "map the customer first name to the tag content",
                                            "tagName": "td",
                                            "lines": [
                                                {
                                                    "from": [
                                                        "customers",
                                                        "firstName"
                                                    ],
                                                    "to": [
                                                        "$tagContent"
                                                    ]
                                                }
                                            ]
                                        },
                                        {
                                            "type": "containerTag",
                                            "comments": "map the customer last name to the tag content",
                                            "tagName": "td",
                                            "lines": [
                                                {
                                                    "from": [
                                                        "customers",
                                                        "lastName"
                                                    ],
                                                    "to": [
                                                        "$tagContent"
                                                    ]
                                                }
                                            ]
                                        },
                                        {
                                            "type": "containerTag",
                                            "comments": "map the customer email to the tag content",
                                            "tagName": "td",
                                            "lines": [
                                                {
                                                    "from": [
                                                        "customers",
                                                        "email"
                                                    ],
                                                    "to": [
                                                        "$tagContent"
                                                    ]
                                                }
                                            ]
                                        }
                                    ],
                                    "tagName": "tr"
                                }
                            ]
                        }
                    ],
                    "tagName": "table"
                }
            ],
            "tagName": "body"
        }
    ]
}