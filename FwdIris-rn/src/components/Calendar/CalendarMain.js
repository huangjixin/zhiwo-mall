import React from 'react';
import {StyleSheet, ScrollView, Text,View} from 'react-native';
import TopCalendar from './TopCalendar';
import TimeLineMain from './TimeLineMain';
class CalendarMain extends React.Component {

    render() {
        return (
           //<TimeLineMain/>
            <TopCalendar/>
        );
    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
    },
});

export default CalendarMain;
