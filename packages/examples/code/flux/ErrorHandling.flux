{
    "comment": "# [Error Handling in Flux](https://docs.torocloud.com/martini/latest/quick-start/resources/examples-package/flux)\n\nA simple [Flux service](https://docs.torocloud.com/martini/developing/services/flux/) demonstrating error-handling.\n\nFrom `Start_State`, the service moves to the `Throw_an_Exception` state, and then to the `Handle_Exception` state. `End_State` is forfeited in favor of `Handle_Exception` because of the latter's `$exception` transition.\n\n## Invoking the Service\n\nUsing the Flux Service editor is the fastest way to [run this service](https://docs.torocloud.com/martini/developing/services/flux/running/). No [inputs](https://docs.torocloud.com/martini/developing/services/overview/input-output/) are required. The only requirement is that you must ensure the service's Start State is set to `Start_State` so it runs as expected.\n\n## Expected Output\n\nUpon running this service, you should see log messages like below in the [Martini console](https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view):\n\n```\nINFO  [Martini] Starting Start_State\nERROR [Martini] Handling exception 'This is a Flux exception!'. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\n```\n",
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
            "comment": "This state is the start state of the Flux service, it just logs the state name.",
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
                        "expression": "\"Starting ${$fluxStateName}\"",
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
                    "to": "Throw_an_Exception"
                }
            ]
        },
        {
            "name": "Throw_an_Exception",
            "displayName": "Throw an Exception",
            "x": 280,
            "y": 120,
            "comment": "This state invokes 'GloopMethods.throwException' to throw an exception.",
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.GloopMethods",
                "methodName": "throwException",
                "parameters": [
                    "String"
                ],
                "inputs": [
                    {
                        "type": "set",
                        "expression": "This is a Flux exception!",
                        "to": [
                            "message"
                        ]
                    }
                ],
                "outputs": [
                    {
                        "type": "set",
                        "expression": "done",
                        "to": [
                            "$fluxEvent"
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
                    "comments": "This transition's event uses the special event name '$exception', this event name indicates to the Flux service that this transition should be used when the state throws an exception and let the next state handle it instead of terminating the Flux.",
                    "lines": [
                        {
                            "from": [
                                "$fluxException"
                            ],
                            "to": [
                                "exception"
                            ]
                        }
                    ],
                    "event": "$exception",
                    "to": "Handle_Exception"
                },
                {
                    "event": "done",
                    "to": "End_State"
                }
            ]
        },
        {
            "name": "Handle_Exception",
            "displayName": "Handle Exception",
            "x": 280,
            "y": 280,
            "comment": "This state handles the input exception and logs the exception message.",
            "action": {
                "type": "invokeCode",
                "className": "io.toro.martini.LoggerMethods",
                "methodName": "error",
                "parameters": [
                    "String"
                ],
                "inputs": [
                    {
                        "type": "set",
                        "expression": "\"Handling exception '${exception.message}'. If you want to see the output in Martini logs, visit https://docs.torocloud.com/martini/latest/setup-and-administration/monitoring/logs/#console-view and check how to view Console logs for the platform you are in.\"",
                        "evaluate": true,
                        "to": [
                            "message"
                        ]
                    }
                ]
            },
            "input": [
                {
                    "name": "exception",
                    "type": "model",
                    "reference": "io.toro.martini.flux.FluxRuntimeException"
                }
            ],
            "output": [
                
            ]
        },
        {
            "name": "End_State",
            "displayName": "End State",
            "x": 440,
            "y": 120,
            "comment": "This state ends the Flux service when no exception was thrown by the previous state and just logs the state name.",
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
                        "expression": "$fluxStateName",
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