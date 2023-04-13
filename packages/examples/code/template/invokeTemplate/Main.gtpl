{
    "type": "template",
    "comments": "This Gloop template demonstrates how to invoke another Gloop template to include it, you can learn more about it [here](https://docs.torocloud.com/martini/latest/developing/gloop-template/step/invoke-template/).\n\nThe Gloop template can be accessed via a browser at `http://<host>:<port>/app/examples/person`",
    "input": [
        {
            "name": "person",
            "type": "model",
            "gloopProperties": [
                {
                    "name": "firstName",
                    "allowNull": false,
                    "defaultValue": "John"
                },
                {
                    "name": "lastName",
                    "allowNull": false,
                    "defaultValue": "Doe"
                },
                {
                    "name": "age",
                    "type": "integer",
                    "allowNull": false,
                    "defaultValue": 18
                },
                {
                    "name": "hobby",
                    "defaultValue": "None"
                }
            ]
        }
    ],
    "paths": [
        "/examples/person"
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
                    "type": "invokeTemplate",
                    "comments": "include the `template.invokeTemplate.Person` template",
                    "templateName": "template.invokeTemplate.Person",
                    "inputs": [
                        {
                            "from": [
                                "person",
                                "firstName"
                            ],
                            "to": [
                                "firstName"
                            ]
                        },
                        {
                            "from": [
                                "person",
                                "lastName"
                            ],
                            "to": [
                                "lastName"
                            ]
                        },
                        {
                            "from": [
                                "person",
                                "age"
                            ],
                            "to": [
                                "age"
                            ]
                        },
                        {
                            "from": [
                                "person",
                                "age"
                            ],
                            "to": [
                                "age"
                            ]
                        },
                        {
                            "from": [
                                "person",
                                "lastName"
                            ],
                            "to": [
                                "lastName"
                            ]
                        },
                        {
                            "from": [
                                "person",
                                "firstName"
                            ],
                            "to": [
                                "firstName"
                            ]
                        }
                    ],
                    "children": [
                        {
                            "type": "containerTag",
                            "comments": "these tags will injected in the invoked template",
                            "children": [
                                {
                                    "type": "containerTag",
                                    "tagName": "p",
                                    "content": "Hobby:"
                                },
                                {
                                    "type": "containerTag",
                                    "comments": "map the person hobby property to the tag content",
                                    "tagName": "p",
                                    "lines": [
                                        {
                                            "from": [
                                                "person",
                                                "hobby"
                                            ],
                                            "to": [
                                                "$tagContent"
                                            ]
                                        }
                                    ]
                                }
                            ],
                            "tagName": "div",
                            "attributes": {
                                "id": "insideGloopTemplateView"
                            }
                        }
                    ]
                }
            ],
            "tagName": "body",
            "attributes": {
                "id": "rootTemplate"
            }
        }
    ]
}