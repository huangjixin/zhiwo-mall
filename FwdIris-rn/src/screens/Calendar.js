import React from 'react';
import {StyleSheet, ScrollView, Text} from 'react-native';
import CalendarMain from '../components/Calendar/CalendarMain'

export class Calendar extends React.Component {

  render() {
    return (
      <CalendarMain/>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
});


