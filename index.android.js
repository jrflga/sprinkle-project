import React, { Component } from 'react';
import Icon from 'react-native-vector-icons/FontAwesome';
import Mapbox from 'react-native-mapbox-gl';
import ToolbarAndroid from 'ToolbarAndroid';
import DrawerLayoutAndroid from 'DrawerLayoutAndroid';
var mapRef = 'mapRef';
import {
  AppRegistry,
  StyleSheet,
  Text,
  ListView,
  View
} from 'react-native';


class Sprinkle extends Component {

    render() {
      var navigationView = (
          <View style={{flex: 1, backgroundColor: '#b3c87a'}}>
              <View style={{height: 200, backgroundColor: '#347A2A'}}>
              </View>
              <View style={{height: 100, width: 100, backgroundColor: '#eee', borderRadius: 50, bottom: 50, left: 100, alignItems: 'center', justifyContent: 'center'}}>
                  <Icon name="envira" size={50} color="#347A2A" />
              </View>
          </View>
        );

    return (
    <View style={styles.container}>
        <DrawerLayoutAndroid
        drawerWidth={300}
        drawerPosition={DrawerLayoutAndroid.positions.Left}
        renderNavigationView={() => navigationView}>
            <Icon.ToolbarAndroid
                style={styles.toolbar}
                title="Sprinkle"
                titleColor="#202e24"
                actions={[{title: 'Account', iconName: 'user', iconColor: 'black', iconSize: 24, show: 'always'}]}
            />
            <Mapbox
                accessToken={'pk.eyJ1IjoianJmbGdhIiwiYSI6ImNpcTg2bDNqejAwc3Vmc20xeWltOThvaTYifQ.yWNGTLAa4yCO2rppxdZwzA'}
                centerCoordinate={{latitude: -22.850, longitude: -43.3044}}
                debugActive={false}
                direction={10}
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
    backgroundColor: '#b3c87a',
    elevation: 2,
  }
});

AppRegistry.registerComponent('Sprinkle', () => Sprinkle);
