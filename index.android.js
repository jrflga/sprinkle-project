import React, { Component } from "react";
import Icon from "react-native-vector-icons/MaterialIcons";
import FontAwesome from "react-native-vector-icons/FontAwesome";
import Mapbox from "react-native-mapbox-gl";
import ToolbarAndroid from "ToolbarAndroid";
import DrawerLayoutAndroid from "DrawerLayoutAndroid";
var mapRef = "mapRef";
import {
    AppRegistry,
    StyleSheet,
    StatusBar,
    Text,
    ListView,
    Dimensions,
    View
} from "react-native";

var mapRef = "mapRef";
var {height, width} = Dimensions.get("window");

class Sprinkle extends Component {

    render() {
        var navigationView = (
            <View style={styles.navigationbottom}>
                <View style={styles.navigationtop}></View>
                <View style={styles.navigationback}>
                    <FontAwesome name="envira" size={50} color="#347a2a" />
                </View>
                <View style={styles.drawerMenu}>
                    <Icon.Button style={{left: 8}} name="info" size={24} backgroundColor="#b3c87a" color="#fefbef" onPress={this.null}>
                    <Text style={styles.drawerButton}> About </Text>
                    </Icon.Button>
                    <Icon.Button style={{left: 8}} name="account-circle" size={24} backgroundColor="#b3c87a" color="#fefbef" onPress={this.null}>
                        <Text style={styles.drawerButton}> Profile </Text>
                    </Icon.Button>
                    <Icon.Button style={{left: 8}} name="settings" size={24} backgroundColor="#b3c87a" color="#fefbef" onPress={this.null}>
                        <Text style={styles.drawerButton}> Settings </Text>
                    </Icon.Button>
                </View>
            </View>
        );

        return (
            <View style={styles.container}>
                <StatusBar backgroundColor="#202e24" />
                <DrawerLayoutAndroid
                    drawerWidth={300}
                    drawerPosition={DrawerLayoutAndroid.positions.Left}
                    renderNavigationView={() => navigationView}>
                    <Icon.ToolbarAndroid
                        style={styles.toolbar}
                        title="Sprinkle"
                        titleColor="#fefbef"
                        actions={[{title: "Account", iconName: "account-circle", iconColor: "#fefbef", iconSize: 32, show: "always"}]}
                    />
                    <Mapbox
                        accessToken={"pk.eyJ1IjoianJmbGdhIiwiYSI6ImNpcTg2bDNqejAwc3Vmc20xeWltOThvaTYifQ.yWNGTLAa4yCO2rppxdZwzA"}
                        annotations={[{coordinates: [-22.927, -43.245], type: "point", title: "NAVE Rio", subtitle: "Núcleo Avançado de Educação"}]}
                        attributionButtonIsHidden={true}
                        centerCoordinate={{latitude: -22.927, longitude: -43.245}}
                        compassIsHidden={false}
                        debugActive={false}
                        direction={0.5}
                        logoIsHidden={true}
                        ref={mapRef}
                        rotateEnabled={true}
                        scrollEnabled={true}
                        style={styles.map}
                        styleURL={"mapbox://styles/jrflga/ciq8donp7001jbkmbps2i7lmg"}
                        showsUserLocation={true}
                        zoomEnabled={true}
                        zoomLevel={13}
                    />
                </DrawerLayoutAndroid>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1
    },
    toolbar: {
        height: 56,
        backgroundColor: "#b3c87a",
        elevation: 5
    },
    navigationtop: {
        height: 200,
        backgroundColor: "#347a2a"
    },
    navigationbottom: {
        flex: 1,
        backgroundColor: "#b3c87a"
    },
    navigationback: {
        height: 100,
        width: 100,
        backgroundColor: "#fdfadf",
        borderRadius: 50,
        bottom: 50,
        left: 100,
        alignItems: "center",
        justifyContent: "center"
    },
    map: {
        flex: 1
    },
    drawerMenu: {
        bottom: 35
    },
    drawerButton: {
        fontSize: 15,
        left: 25,
        color: "#fefbef",
        fontWeight: "bold"
    },
});

AppRegistry.registerComponent("Sprinkle", () => Sprinkle);
