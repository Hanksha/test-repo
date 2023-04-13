{
    "type": "template",
    "comments": "Root Gloop template for the todo app. The Gloop template can be accessed via a browser at `http://<host>:<port>/app/todo`.\n\nThe \"todo\" application is a simple CRUD application where the user can add, delete and set todo items as completed. \n\nIt demonstrates many features of Gloop templates:\n\n- Using tags\n- Retrieving and displaying data\n- Invoking other Gloop templates\n- Styling via css",
    "paths": [
        "/todo"
    ],
    "methods": [
        "GET"
    ],
    "steps": [
        {
            "type": "invokeGloop",
            "comments": "Retrieve all the todo items",
            "name": "template.todoapp.api.GetTodos",
            "declare": [
                {
                    "variables": [
                        {
                            "name": "todos",
                            "type": "model",
                            "array": true,
                            "reference": "template.todoapp.Todo"
                        }
                    ]
                }
            ],
            "outputs": [
                {
                    "from": [
                        "todos"
                    ],
                    "to": [
                        "todos"
                    ]
                }
            ]
        },
        {
            "type": "invokeTemplate",
            "comments": "include the `template.todoapp.Head` template",
            "templateName": "template.todoapp.Head",
            "children": [
                {
                    "type": "containerTag",
                    "comments": "set the page title",
                    "tagName": "title",
                    "content": "Todo App"
                }
            ]
        },
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "containerTag",
                    "children": [
                        {
                            "type": "containerTag",
                            "comments": "form to submit a new todo, the todo is submitted to `/api/todo/add` which will be redirected back here once the todo is added\n",
                            "children": [
                                {
                                    "type": "containerTag",
                                    "tagName": "input",
                                    "attributes": {
                                        "type": "text",
                                        "placeholder": "Enter an activity",
                                        "id": "item",
                                        "name": "name",
                                        "minlength": "3"
                                    }
                                },
                                {
                                    "type": "containerTag",
                                    "children": [
                                        {
                                            "type": "containerTag",
                                            "tagName": "div",
                                            "attributes": {
                                                "class": "add-btn"
                                            },
                                            "content": ""
                                        }
                                    ],
                                    "tagName": "button",
                                    "attributes": {
                                        "id": "add",
                                        "type": "submit"
                                    },
                                    "content": ""
                                }
                            ],
                            "tagName": "form",
                            "attributes": {
                                "action": "/api/todo/add",
                                "method": "post"
                            }
                        }
                    ],
                    "tagName": "header"
                },
                {
                    "type": "containerTag",
                    "children": [
                        {
                            "type": "containerTag",
                            "children": [
                                {
                                    "type": "iterate",
                                    "comments": "iterate the todos, filter out todos that are completed",
                                    "input": {
                                        "path": [
                                            "todos"
                                        ]
                                    },
                                    "children": [
                                        {
                                            "type": "fork",
                                            "comments": "only not completed todos",
                                            "expression": "!todos.completed",
                                            "children": [
                                                {
                                                    "type": "invokeTemplate",
                                                    "label": "true",
                                                    "comments": "invoke the `template.todoapp.TodoListItem` and pass it the todo",
                                                    "templateName": "template.todoapp.TodoListItem",
                                                    "inputs": [
                                                        {
                                                            "from": [
                                                                "todos"
                                                            ],
                                                            "to": [
                                                                "todo"
                                                            ]
                                                        },
                                                        {
                                                            "from": [
                                                                "todos"
                                                            ],
                                                            "to": [
                                                                "todo"
                                                            ]
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ],
                            "tagName": "ul",
                            "attributes": {
                                "class": "todo",
                                "id": "todo"
                            }
                        },
                        {
                            "type": "containerTag",
                            "children": [
                                {
                                    "type": "iterate",
                                    "comments": "iterate the todos, filter out todos that are not completed",
                                    "input": {
                                        "path": [
                                            "todos"
                                        ]
                                    },
                                    "children": [
                                        {
                                            "type": "fork",
                                            "comments": "only completed todos",
                                            "expression": "todos.completed",
                                            "children": [
                                                {
                                                    "type": "invokeTemplate",
                                                    "label": "true",
                                                    "comments": "invoke the `template.todoapp.TodoListItem` and pass it the todo",
                                                    "templateName": "template.todoapp.TodoListItem",
                                                    "inputs": [
                                                        {
                                                            "from": [
                                                                "todos"
                                                            ],
                                                            "to": [
                                                                "todo"
                                                            ]
                                                        },
                                                        {
                                                            "from": [
                                                                "todos"
                                                            ],
                                                            "to": [
                                                                "todo"
                                                            ]
                                                        }
                                                    ]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            ],
                            "tagName": "ul",
                            "attributes": {
                                "class": "todo",
                                "id": "completed"
                            }
                        }
                    ],
                    "tagName": "div",
                    "attributes": {
                        "class": "container"
                    }
                }
            ],
            "tagName": "body"
        }
    ]
}