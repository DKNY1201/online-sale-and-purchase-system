import React from 'react';

import Logo from '../../Logo/Logo';
import NavigationItems from '../NavigationItems/NavigationItems';
import classes from './SideDrawer.css';
import Aux from '../../../hoc/Auxi/Aux';
import BackDrop from '../../UI/Backdrop/Backdrop';

const sideDrawer = (props) => {
    let assignedClass = [classes.SideDrawer]
    if (props.open) {
        assignedClass.push(classes.Open);
    } else {
        assignedClass.push(classes.Close);
    }

    return (
        <Aux>
            <BackDrop show={props.open} clicked={props.closeSideDrawer} />
            <div className={assignedClass.join(' ')} onClick={props.closeSideDrawer}>
                <div className={classes.Logo}>
                    <Logo />
                </div>
                <nav>
                    <NavigationItems isAuthenticated={props.isAuthenticated} />
                </nav>
            </div>
        </Aux>
    );
}

export default sideDrawer;