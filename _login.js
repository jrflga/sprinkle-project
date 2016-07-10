import React, { Component } from "react";
import {
    AppRegistry,
    StyleSheet,
    StatusBar,
    Text,
    TextInput,
    TouchableNativeFeedback,
    View
} from "react-native";

var AwesomeProject = React.createClass({
    focusNextField(nextField) {
        this.refs[nextField].focus();
    },
    finishSubmit() {
        var _done = true;
        for (var i = 0, l = this.refs.length; i < l; i++) {
            if (this.refs[i].value && this.refs[i].value.trim() == "") {
                _done = false;
            }
        }
        if (!_done) {
            //
        } else {
            return fetch("http://example.com", {
                method: "POST",
                body: {
                    uname: "username",
                    pw: "password"
                }
            }).then((response) => (response._bodyText)).catch((error) => {
                console.error(error);
            });
        }
    },
    render: function() {
        return (
            <View style={{backgroundColor:"#ebe8be", flex: 1}}>
                <StatusBar backgroundColor="#202e24" />
                <TextInput 
                    ref="1"
                    placeholder="Email"
                    keyboardType="email-address"
                    style={styles.email}
                    blurOnSubmit={false}
                    onSubmitEditing={ () => this.focusNextField("2") }
                />
                <TextInput
                    ref="2"
                    placeholder="Password"
                    style={styles.password}
                    password={true} 
                    secureTextEntry={true}
                    blurOnSubmit={true}
                    onSubmitEditing={ () => this.finishSubmit() }
                />
                <View style={styles.button}>
                    <TouchableNativeFeedback
                        onPress={this.finishSubmit}
                        background={TouchableNativeFeedback.SelectableBackground()}>
                        <View style={{width: 150, height: 50, backgroundColor: "#b3c87a", justifyContent: "center", alignItems: "center"}}>
                           <Text style={{margin: 30, color: "white"}}>Login</Text>
                        </View>
                    </TouchableNativeFeedback>
                </View>
            </View>
        );
    },
});

const styles = StyleSheet.create({
    email: {
        fontSize: 16,
        padding: 7,
        paddingLeft: 10,
        marginTop:200,
        marginLeft: 30,
        marginRight: 30,
    },
    password: {
        fontSize: 16,
        padding: 7,
        paddingLeft: 10,
        marginLeft: 30,
        marginRight: 30,
    },
    button: {
        backgroundColor: "#ebe8be", 
        flex: 1, flexDirection: "column", 
        justifyContent: "center", 
        alignItems: "center", 
        marginTop: 50
    }
});

AppRegistry.registerComponent("AwesomeProject", () => AwesomeProject);
