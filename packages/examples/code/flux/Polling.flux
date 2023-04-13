{
    "comment": "# [Polling States](https://docs.torocloud.com/martini/latest/quick-start/resources/examples-package/flux/)\n\nThis [Flux service](https://docs.torocloud.com/martini/developing/services/flux/) demonstrates the use of a loop back transition and wait expression with a duration.\n\n## Invoking the Service\n\nUsing the Flux Service editor is the fastest way to [run this service](https://docs.torocloud.com/martini/developing/services/flux/running/). No [inputs](https://docs.torocloud.com/martini/developing/services/overview/input-output/) are required. The only requirement is that you must ensure the service's Start State is set to `Start_State` so it runs as expected.\n\n## Expected Output\n\n`Polling_State` generates a random number. If the number is equal to 4, it will set `$fluxEvent` to `done`. If not, it loops back to itself after 5 seconds repeating the process.\n\nThe number of tries it logs depends on how many iterations the random number generator took to generate number 4.\n\nIn the [Martini logs](https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view), you will see something like:\n\n```\nINFO  [Martini] Flux start. The next state will generate a random number. If the generated number is not equal 4, it will keep on polling every 5 seconds until the generated number is equal to 4.\nINFO  [Martini] Polling 1\nINFO  [Martini] Polling 2\n...\nINFO  [Martini] Polling ended, transitioning to End_State. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\n```\n",
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
            "x": 120,
            "y": 120,
            "comment": "This state is the start state of the Flux service and directly transition to the next state.",
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.LoggerMethods",
                "methodName": "info",
                "parameters": [
                    "java.lang.String"
                ],
                "inputs": [
                    {
                        "type": "set",
                        "expression": "Flux start. The next state will generate a random number. If the generated number is not equal 4, it will keep on polling every 5 seconds until the generated number is equal to 4.",
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
                    "to": "Polling_State"
                }
            ]
        },
        {
            "name": "Polling_State",
            "displayName": "Polling State",
            "x": 280,
            "y": 120,
            "comment": "This state polls a random number, if the number is equal to 4 it will set the $fluxEvent to 'done', if not it uses a loop back transition to transition to itself after 5 seconds.",
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
                        "expression": "\"Polling ${pollingCount + 1}\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ],
                "outputs": [
                    {
                        "type": "set",
                        "expression": "pollingCount + 1",
                        "evaluate": true,
                        "to": [
                            "pollingCount"
                        ]
                    },
                    {
                        "type": "set",
                        "expression": "(new Random().nextInt(5) == 4 ? 'done':'')",
                        "evaluate": true,
                        "to": [
                            "$fluxEvent"
                        ]
                    }
                ]
            },
            "input": [
                
            ],
            "output": [
                {
                    "name": "pollingCount",
                    "type": "integer",
                    "defaultValue": 0
                }
            ],
            "transitions": [
                {
                    "comments": "This transition is a loop back transition, it loops back to the state it is originating from, additionally it uses a wait expression with a duration of 5 seconds to delay the transition. The '$else' event means it transitions if the event did not match any of the other transition of the state.",
                    "event": "$else",
                    "wait": "duration:5",
                    "to": "Polling_State"
                },
                {
                    "event": "done",
                    "to": "End_State"
                }
            ]
        },
        {
            "name": "End_State",
            "displayName": "End State",
            "x": 280,
            "y": 280,
            "comment": "This state ends the Flux service and logs the state name.",
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
                        "expression": "\"Polling ended, transitioning to ${$fluxStateName}. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\"",
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
        }
    ]
}