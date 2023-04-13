{
    "type": "template",
    "comments": "This Gloop template is used to display a given todo item. It is meant to be invoked by other Gloop template like `examples/template/todoapp/Index.gtpl`.\n\nIt demonstrates how a Gloop template can be used as a reusable component.\n\nThis Gloop template itself makes us of forms to post action to a Gloop API in order to delete or modify the todo.",
    "input": [
        {
            "name": "todo",
            "type": "model",
            "reference": "template.todoapp.Todo"
        }
    ],
    "steps": [
        {
            "type": "containerTag",
            "children": [
                {
                    "type": "containerTag",
                    "children": [
                        {
                            "type": "containerTag",
                            "comments": "form action that post to `/api/todo/{id}` in order to delete the todo",
                            "children": [
                                {
                                    "type": "containerTag",
                                    "tagName": "button",
                                    "attributes": {
                                        "class": "remove"
                                    },
                                    "content": ""
                                }
                            ],
                            "tagName": "form",
                            "attributes": {
                                "action": "",
                                "method": "get"
                            },
                            "lines": [
                                {
                                    "type": "set",
                                    "expression": "\"/api/todo/${todo.id}\"",
                                    "evaluate": true,
                                    "to": [
                                        "action"
                                    ]
                                }
                            ]
                        },
                        {
                            "type": "containerTag",
                            "comments": "form action that post to `/api/todo/completed` in order to set the todo as completed or not",
                            "children": [
                                {
                                    "type": "containerTag",
                                    "tagName": "button",
                                    "attributes": {
                                        "class": "complete"
                                    },
                                    "content": ""
                                }
                            ],
                            "tagName": "form",
                            "attributes": {
                                "action": "",
                                "method": "post"
                            },
                            "lines": [
                                {
                                    "type": "set",
                                    "comments": "use a groovy expression to set the `completed` query parameter as true or false",
                                    "expression": "\"/api/todo/completed/${todo.id}?completed=${(!todo.completed).toString()}\"",
                                    "evaluate": true,
                                    "to": [
                                        "action"
                                    ]
                                }
                            ]
                        }
                    ],
                    "tagName": "div",
                    "attributes": {
                        "class": "buttons"
                    }
                }
            ],
            "tagName": "li",
            "lines": [
                {
                    "from": [
                        "todo",
                        "name"
                    ],
                    "to": [
                        "$tagContent"
                    ]
                }
            ],
            "content": ""
        }
    ]
}