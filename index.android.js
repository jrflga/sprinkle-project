/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

var ToolbarAndroid = require('ToolbarAndroid');

class Sprinkle extends Component {
  render() {
    return (
    <View style={styles.container}>
        <ToolbarAndroid
        style={styles.toolbar}
        title="Sprinkle"
        titleColor="#202e24"
        actions={[{title: 'Account'}]}
        />
        <View style={styles.map}>
            <Text style={styles.mapText}>
                Content
            </Text>
        </View>
    </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#ebe8be',
  },
  toolbar: {
    height: 56,
    backgroundColor: '#b3c87a',
    elevation: 3
  },
  map: {
    alignItems: 'center',
    justifyContent: 'center',
    height: 100,
    backgroundColor: '#C4C193'
  },
  mapText: {
    fontSize: 25,
    fontFamily: 'monospace'
  }
});

AppRegistry.registerComponent('Sprinkle', () => Sprinkle);
