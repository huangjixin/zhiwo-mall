import React from 'react';
import {StyleSheet, ScrollView, Text,View,Image, DeviceEventEmitter,TouchableWithoutFeedback} from 'react-native';
import {Calendar} from 'react-native-calendars';
import  BottomBalendarList from './BottomBalendarList';
class TopCalendar extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
        this.onDayPress = this.onDayPress.bind(this);
    }
    calendarToTimeLine=() =>{
        DeviceEventEmitter.emit('calendarToTimeLine');
    }
    render() {
        return (/*
            <ScrollView style={styles.container}>
                <Text style={styles.text}>Calendar with selectable date and arrows</Text>
                <Calendar
                    onDayPress={this.onDayPress}
                    style={styles.calendar}
                    hideExtraDays
                    markedDates={{[this.state.selected]: {selected: true, disableTouchEvent: true, selectedDotColor: 'orange'}}}
                />
                <Text style={styles.text}>Calendar with marked dates and hidden arrows</Text>
               */

            <ScrollView style={styles.container}>
                <View style={{flex: 1, flexDirection: 'row',
                    justifyContent: 'space-between',height:60}}>
                        <Image source={require('../../../img/Calendar/u572.png')}
                        style={{width:32,height:32,marginTop:10,marginLeft:10,}}/>
                        <Text style={{fontSize:20,marginTop:10}}>日历</Text>
                    <TouchableWithoutFeedback   onPress={this.calendarToTimeLine}>
                        <Image source={require('../../../img/Calendar/u876.png')}
                               style={{marginTop:10,marginRight:10,}} />
                    </TouchableWithoutFeedback    >
                </View>
                <Calendar
                    style={styles.calendar}
                    current={'2018-04-12'}
                    minDate={'2018-04-01'}
                    maxDate={'2018-04-30'}
                    firstDay={1}
                    /*renderArrow={() => (
                        <View><Text>aaaaaaa</Text></View>
                    )}*/
                    markedDates={{
                        '2018-04-23': {selected: true, marked: true,selectedColor: '#FF8671'},
                        '2018-04-24': {selected: true, marked: true, dotColor: '#999999',selectedColor: '#FF8671'},
                        '2018-04-25': {marked: true, dotColor: '#999999'},
                        '2018-04-26': {marked: true, dotColor: '#999999'},
                        '2018-04-27': {disabled: true, activeOpacity: 0}
                    }}
                    // disabledByDefault={true}
                    hideArrows={false}
                />
                <BottomBalendarList/>



            </ScrollView>
            /*
            <Text style={styles.text}>Calendar with custom day component</Text>
            <Calendar
                style={[styles.calendar, {height: 300}]}
                dayComponent={({date, state}) => {
                    return (<View style={{flex: 1}}><Text style={{textAlign: 'center', color: state === 'disabled' ? 'gray' : 'black'}}>{date.day}</Text></View>);
                }}
            />
            <Text style={styles.text}>Calendar with period marking and spinner</Text>
            <Calendar
                style={styles.calendar}
                current={'2012-05-16'}
                minDate={'2012-05-10'}
                displayLoadingIndicator
                markingType={'period'}
                theme={{
                    calendarBackground: '#333248',
                    textSectionTitleColor: 'white',
                    dayTextColor: 'red',
                    todayTextColor: 'white',
                    selectedDayTextColor: 'white',
                    monthTextColor: 'white',
                    selectedDayBackgroundColor: '#333248',
                    arrowColor: 'white',
                    // textDisabledColor: 'red',
                    'stylesheet.calendar.header': {
                        week: {
                            marginTop: 5,
                            flexDirection: 'row',
                            justifyContent: 'space-between'
                        }
                    }
                }}
                markedDates={{
                    '2012-05-17': {disabled: true},
                    '2012-05-08': {textColor: '#666'},
                    '2012-05-09': {textColor: '#666'},
                    '2012-05-14': {startingDay: true, color: 'blue', endingDay: true},
                    '2012-05-21': {startingDay: true, color: 'blue'},
                    '2012-05-22': {endingDay: true, color: 'gray'},
                    '2012-05-24': {startingDay: true, color: 'gray'},
                    '2012-05-25': {color: 'gray'},
                    '2012-05-26': {endingDay: true, color: 'gray'}}}
                hideArrows={false}
            />
            <Text style={styles.text}>Calendar with multi-dot marking</Text>
            <Calendar
                style={styles.calendar}
                current={'2012-05-16'}
                markingType={'multi-dot'}
                markedDates={{
                    '2012-05-08': {dots: [{key: 'vacation', color: 'blue', selectedDotColor: 'white'}, {key: 'massage', color: 'red', selectedDotColor: 'white'}], selected: true},
                    '2012-05-09': {dots: [{key: 'vacation', color: 'blue', selectedDotColor: 'red'}, {key: 'massage', color: 'red', selectedDotColor: 'blue'}], disabled: true}
                }}
                hideArrows={false}
            />
            <Text style={styles.text}>Calendar with week numbers</Text>
            <Calendar
                onDayPress={this.onDayPress}
                style={styles.calendar}
                hideExtraDays
                showWeekNumbers
                markedDates={{[this.state.selected]: {selected: true}}}
            />
        </ScrollView>*/
        );
    }

    onDayPress(day) {
        this.setState({
            selected: day.dateString
        });
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor:'white',
    },
    calendar: {
        borderTopWidth: 1,
        paddingTop: 5,
        borderBottomWidth: 1,
        borderColor: '#eee',
        height: 350,
    },
    text: {
        textAlign: 'center',
        borderColor: '#bbb',
        padding: 10,
        backgroundColor: '#eee'
    },
});

export default TopCalendar;
