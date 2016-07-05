import React, { Component } from "react";
import Icon from "react-native-vector-icons/FontAwesome";
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
    View
} from "react-native";


class Sprinkle extends Component {

    render() {
        var navigationView = (
            <View style={styles.navigationbottom}>
                <View style={styles.navigationtop}></View>
                <View style={styles.navigationback}>
                    <Icon name="envira" size={50} color="#347a2a" />
                </View>
                <View style={styles.drawerMenu}>
                    <Icon.Button name="exclamation-circle" size={30} backgroundColor="#b3c87a" color="#202e24" onPress={this.null}>
                    <Text style={styles.drawerButton}> About </Text>
                    </Icon.Button>
                    <Icon.Button name="user" size={30} backgroundColor="#b3c87a" color="#202e24" onPress={this.null}>
                        <Text style={styles.drawerButton}> Profile </Text>
                    </Icon.Button>
                    <Icon.Button name="cog" size={30} backgroundColor="#b3c87a" color="#202e24" onPress={this.null}>
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
                        titleColor="#202e24"
                        actions={[{title: "Account", iconName: "user", iconColor: "#202e24", iconSize: 24, show: "always"}]}
                    />
                    <Mapbox
                        accessToken={"pk.eyJ1IjoianJmbGdhIiwiYSI6ImNpcTg2bDNqejAwc3Vmc20xeWltOThvaTYifQ.yWNGTLAa4yCO2rppxdZwzA"}
                        centerCoordinate={{latitude: -22.924, longitude: -43.245}}
                        debugActive={false}
                        direction={0.5}
                        ref={mapRef}
                        rotateEnabled={false}
                        scrollEnabled={true}
                        style={styles.container}
                        styleURL={"mapbox://styles/jrflga/ciq8donp7001jbkmbps2i7lmg"}
                        showsUserLocation={true}
                        zoomEnabled={true}
                        zoomLevel={13}
                        compassIsHidden={false}
                        attributionButtonIsHidden={true}
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
        elevation: 2,
    },
    navigationtop: {
        height: 200,
        backgroundColor: "#347a2a",
    },
    navigationbottom: {
        flex: 1,
        backgroundColor: "#b3c87a",
    },
    navigationback: {
        height: 100,
        width: 100,
        backgroundColor: "#eeeeee",
        borderRadius: 50,
        bottom: 50,
        left: 100,
        alignItems: "center",
        justifyContent: "center",
    },
    drawerMenu: {
        bottom: 35
    },
    drawerButton: {
        fontSize: 20,
        left: 5,
        color: "#0a0a0a"
    },
});

AppRegistry.registerComponent("Sprinkle", () => Sprinkle);
