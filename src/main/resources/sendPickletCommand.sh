#!/bin/bash

# Set the address, command, and color values as variables
address=$1
command=$2
red=$3
green=$4
blue=$5
yellow=$6

# Create a JSON file with the input values
cat > pickletCommandMock.json << EOL
{
    "address": "$address",
    "command": "$command",
    "red": $red,
    "blue": $blue,
    "green": $green,
    "yellow": $yellow
}
EOL

# Send the JSON file to the specified endpoint
curl -v -X POST -H "Content-Type: application/json" -d @pickletCommandMock.json "http://localhost:8000/test"

