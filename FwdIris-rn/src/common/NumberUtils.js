

export function toMoneyStr(num, precision){
    if(num==null){
        return "0"
    }

    let tpMoney = new Number(num);
    if (isNaN(tpMoney)) {
        return "0";
    }

    if(precision!=null)
        tpMoney = tpMoney.toFixed(precision);

    let numStr = new String(tpMoney);
    let reg = /^(-?\d+)(\d{3})(\.?\d*)/;
    while (reg.test(numStr)) {
        numStr = numStr.replace(reg, "$1,$2$3")
    }

    return numStr.toString();
}

export function toLargeMoneyStr(num, precision){

    let numStr = toMoneyStr(num,precision);

    const lastIndex = numStr.lastIndexOf(',');
    const firstIndex = numStr.indexOf(',');
    if(lastIndex!=firstIndex){
        const subStr = numStr.substring(0,lastIndex);
        numStr = subStr+'K';
    }

    return numStr;
}

export  function calcBalance(target,num) {
    const result = target - num;
    return toMoneyStr(result<0?0:result);
}


export function hiddenBankCard(str){
    let reg = /(\d{12})/;
    if(!reg.test(str))
        return '';
    return str.replace(reg,'****  ****  ****  ');
}

/*保留小数点后两位*/
export function divisionDot2(molecule,denominator) {
    let percent = molecule/denominator;
    return percent.toFixed(2);
}