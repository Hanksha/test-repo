{
    "comment": "# [Passing Data Between States](https://docs.torocloud.com/martini/latest/quick-start/resources/examples-package/flux)\n\nThis [Flux service](https://docs.torocloud.com/martini/developing/services/flux/) demonstrates [how to pass data from one state to another](https://docs.torocloud.com/martini/developing/services/flux/building/#passing-data-from-one-state-to-another) using map lines and Flux transitions. \n\n## Invoking the Service\n\n[Run this Flux](https://docs.torocloud.com/martini/developing/services/flux/running/) as you normally would. In the Input dialog, ensure the Start State is set to `Check_Product_Availability`. You may also set the `order` model, or just use its default property values.\n\n## Expected Output\n\nThe behavior of this service depends on the provided `order`.\n\nIf `order.productId` is `productA`, then the service will return a string property `orderId` whose value will be a randomly-generated UUID string. In addition to this, a log message similar to below will be shown in the [Martini console](https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view)\n\n```\nINFO  [Martini] Registering order, product name: 'ProductA', quantity: 1, total price: $10. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\n```\n\nIf `order.productId` is `productB`, then the service will return a `null` value for the output property `orderId`. A log message similar to the one below will also be displayed in the console:\n\n```\nINFO  [Martini] Discarding order, product 'productB' not available until Wed May 15 00:00:00 PHT 2019. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\n```",
    "input": [
        {
            "name": "order",
            "type": "model",
            "allowNull": false,
            "reference": "flux.model.Order"
        },
        {
            "name": "$trackerId",
            "comments": "Used for tracking the flux service"
        }
    ],
    "output": [
        {
            "name": "orderId"
        }
    ],
    "states": [
        {
            "name": "Check_Product_Availability",
            "displayName": "Check Product Availability",
            "x": 200,
            "y": 40,
            "comment": "This state processes the input order and checks for the availability of the product. \n\nThe 'available' output boolean property is used as event name and directly mapped to the '$fluxEvent' property.",
            "action": {
                "type": "invokeGloop",
                "name": "flux.services.CheckProductAvailability",
                "inputs": [
                    {
                        "from": [
                            "order"
                        ],
                        "to": [
                            "order"
                        ]
                    }
                ],
                "outputs": [
                    {
                        "from": [
                            "available"
                        ],
                        "to": [
                            "$fluxEvent"
                        ]
                    },
                    {
                        "from": [
                            "product"
                        ],
                        "to": [
                            "product"
                        ]
                    },
                    {
                        "from": [
                            "nextRestock"
                        ],
                        "to": [
                            "nextRestock"
                        ]
                    }
                ]
            },
            "input": [
                
            ],
            "output": [
                {
                    "name": "product",
                    "type": "model",
                    "reference": "flux.model.Product"
                },
                {
                    "name": "nextRestock",
                    "type": "date"
                }
            ],
            "transitions": [
                {
                    "comments": "This transition maps the output product from the \"Check Product Availability\" state to input product of the \"Register Order\" state. It's possible to map model with same name as long as they belong to different Flux state.",
                    "lines": [
                        {
                            "from": [
                                "product"
                            ],
                            "to": [
                                "product"
                            ]
                        }
                    ],
                    "event": "true",
                    "to": "Register_Order"
                },
                {
                    "comments": "This transition maps the output date 'nextRestock' from the \"Check Product Availability\" state to the input date 'nextAvailable' of the \"Discard Order\" state.",
                    "lines": [
                        {
                            "from": [
                                "nextRestock"
                            ],
                            "to": [
                                "nextAvailable"
                            ]
                        }
                    ],
                    "event": "false",
                    "to": "Discard_Order"
                }
            ]
        },
        {
            "name": "Register_Order",
            "displayName": "Register Order",
            "x": 120,
            "y": 200,
            "comment": "This state registers the order by just logging the input order and setting the 'orderId' with a random ID using a set step expression.",
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.LoggerMethods",
                "methodName": "info",
                "parameters": [
                    "String"
                ],
                "inputs": [
                    {
                        "type": "set",
                        "expression": "\"Registering order, product name: '${product.name}', quantity: ${order.quantity}, total price: \\$${product.price * order.quantity}. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ],
                "outputs": [
                    {
                        "type": "set",
                        "expression": "UUID.randomUUID().toString()",
                        "evaluate": true,
                        "to": [
                            "orderId"
                        ]
                    }
                ]
            },
            "input": [
                {
                    "name": "product",
                    "type": "model",
                    "reference": "flux.model.Product"
                }
            ],
            "output": [
                
            ]
        },
        {
            "name": "Discard_Order",
            "displayName": "Discard Order",
            "x": 280,
            "y": 200,
            "comment": "This state discards the order and logs the 'nextAvailable' input property.",
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.LoggerMethods",
                "methodName": "info",
                "parameters": [
                    "String"
                ],
                "inputs": [
                    {
                        "type": "set",
                        "expression": "\"Discarding order, product '${order.productId}' not available until $nextAvailable. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ]
            },
            "input": [
                {
                    "name": "nextAvailable",
                    "type": "date"
                }
            ],
            "output": [
                
            ]
        }
    ]
}