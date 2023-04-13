{
    "comment": "# [Waiting for External Events](https://docs.torocloud.com/martini/developing/services/flux)\n\nThis [Flux service](https://docs.torocloud.com/martini/developing/services/flux/) demonstrates how to wait for a Flux event and process it.\n\nFrom `Start_State`, this Flux will transition to `Process_Event_State`. `Process_Event_State` will wait for a `greeting` event. If received within 20 seconds, the Flux transitions to `End_State`. Otherwise, it moves to the `Expired_State`.\n\n## Invoking the Service\n\n[Run this Flux](https://docs.torocloud.com/martini/developing/services/flux/running/) as you normally would. Ensure the Start State is set to `Start_State`. Upon transitioning to `Process_Event_State`, you can either:\n\n- Do nothing and wait for 20 seconds to pass; or\n- Send a `greeting` event with the data:\n\n    ```\n    {\n        \"message\": \"Hello!\"\n    }\n    ```\n\n## Expected Output\n\nIf a `greeting` event was sent within 20 seconds after the Flux transitioned to `Process_Event_State`, the [console will display a message like](https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view):\n\n```\nINFO  [Martini] Starting Start_State. Transitioning to Process Event State and will wait for external event for 20 seconds\nINFO  [Martini] Message received in Process_Event_State: Hello!\n```\n\nHowever, if no event was sent within the 20 second time limit, log messages like below will be displayed instead:\n\n```\nINFO  [Martini] Starting Start_State. Transitioning to Process Event State and will wait for external event for 20 seconds\nWARN  [Martini] No event was received in the last 20 seconds. Process Event State will now transition to Expired_State\n```",
    "input": [
        {
            "name": "$trackerId",
            "comments": "Used for tracking the flux service"
        }
    ],
    "states": [
        {
            "name": "Start_State",
            "displayName": "Start State",
            "x": 200,
            "y": 40,
            "comment": "This state is the start state of the Flux service and logs the state name",
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
                        "expression": "\"Starting ${$fluxStateName}. Transitioning to Process Event State and will wait for external event for 20 seconds\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ]
            },
            "input": [
                
            ],
            "output": [
                
            ],
            "transitions": [
                {
                    "to": "Process_Event_State"
                }
            ]
        },
        {
            "name": "Process_Event_State",
            "displayName": "Process Event State",
            "x": 200,
            "y": 200,
            "comment": "This state is waiting for the event 'greeting', if the event is received within 20 seconds it will transition to 'End State'. To send an event right click the state in the Flux editor.",
            "waiting": true,
            "action": {
                "lines": [
                    {
                        "type": "set",
                        "expression": "\"Message received in ${$fluxStateName}: $message\"",
                        "evaluate": true,
                        "to": [
                            "outputMessage"
                        ]
                    }
                ]
            },
            "input": [
                {
                    "name": "message"
                }
            ],
            "output": [
                {
                    "name": "outputMessage"
                }
            ],
            "transitions": [
                {
                    "comments": "This transition waits for 20 seconds before to transition to the next state.",
                    "wait": "duration:20",
                    "to": "Expired_State"
                },
                {
                    "comments": "This transition will transition to the next state when the event is 'greeting'.",
                    "lines": [
                        {
                            "from": [
                                "outputMessage"
                            ],
                            "to": [
                                "myMessage"
                            ]
                        }
                    ],
                    "event": "greeting",
                    "to": "End_State"
                }
            ]
        },
        {
            "name": "Expired_State",
            "displayName": "Expired State",
            "x": 40,
            "y": 280,
            "comment": "This state is reached when no 'greeting' event was received after 20 seconds.",
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.LoggerMethods",
                "methodName": "warn",
                "parameters": [
                    "String"
                ],
                "inputs": [
                    {
                        "type": "set",
                        "expression": "\"No event was received in the last 20 seconds. Process Event State will now transition to ${$fluxStateName}\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ]
            },
            "input": [
                
            ],
            "output": [
                
            ]
        },
        {
            "name": "End_State",
            "displayName": "End State",
            "x": 360,
            "y": 280,
            "comment": "This state logs the input message.",
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.LoggerMethods",
                "methodName": "info",
                "parameters": [
                    "String"
                ],
                "inputs": [
                    {
                        "from": [
                            "myMessage"
                        ],
                        "to": [
                            "message"
                        ]
                    }
                ]
            },
            "input": [
                {
                    "name": "myMessage"
                }
            ],
            "output": [
                
            ]
        }
    ]
}