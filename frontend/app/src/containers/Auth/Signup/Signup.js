import React, {Component} from 'react';
import {connect} from 'react-redux';
import {Redirect} from 'react-router-dom';

import Input from '../../../components/UI/Input/Input';
import Button from '../../../components/UI/Button/Button';
import classes from './Signup.css';
import * as actions from '../../../store/actions';
import Spinner from '../../../components/UI/Spinner/Spinner';
import * as errors from '../../../shared/error-code-to-message';
import {updateObject, checkValidity} from '../../../shared/utility';

class Auth extends Component {
    state = {
        controls: {
            email: {
                elementType: 'input',
                elementConfig: {
                    type: 'email',
                    placeholder: 'Your Email'
                },
                value: '',
                validation: {
                    rules: {
                        require: true,
                        isEmail: true
                    },
                    valid: false
                },
                touch: false
            },
            firstName: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'First name'
                },
                value: '',
                validation: {
                    rules: {
                        require: true
                    },
                    valid: false
                },
                touch: false
            },
            lastName: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Last name'
                },
                value: '',
                validation: {
                    rules: {
                        require: true
                    },
                    valid: false
                },
                touch: false
            },
            password: {
                elementType: 'input',
                elementConfig: {
                    type: 'password',
                    placeholder: 'Password'
                },
                value: '',
                validation: {
                    rules: {
                        require: true,
                        minLength: 8
                    },
                    valid: false
                },
                touch: false
            },
            phone: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Phone number'
                },
                value: '',
                validation: {
                    rules: {
                        require: true
                    },
                    valid: false
                },
                touch: false
            },
            street: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'Street'
                },
                value: '',
                validation: {
                    rules: {
                        require: true
                    },
                    valid: false
                },
                touch: false
            },
            city: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'City'
                },
                value: '',
                validation: {
                    rules: {
                        require: true
                    },
                    valid: false
                },
                touch: false
            },
            state: {
                elementType: 'select',
                elementConfig: {
                    options: [
                        {
                            "displayValue": "Alabama",
                            "value": "AL"
                        },
                        {
                            "displayValue": "Alaska",
                            "value": "AK"
                        },
                        {
                            "displayValue": "American Samoa",
                            "value": "AS"
                        },
                        {
                            "displayValue": "Arizona",
                            "value": "AZ"
                        },
                        {
                            "displayValue": "Arkansas",
                            "value": "AR"
                        },
                        {
                            "displayValue": "California",
                            "value": "CA"
                        },
                        {
                            "displayValue": "Colorado",
                            "value": "CO"
                        },
                        {
                            "displayValue": "Connecticut",
                            "value": "CT"
                        },
                        {
                            "displayValue": "Delaware",
                            "value": "DE"
                        },
                        {
                            "displayValue": "District Of Columbia",
                            "value": "DC"
                        },
                        {
                            "displayValue": "Federated States Of Micronesia",
                            "value": "FM"
                        },
                        {
                            "displayValue": "Florida",
                            "value": "FL"
                        },
                        {
                            "displayValue": "Georgia",
                            "value": "GA"
                        },
                        {
                            "displayValue": "Guam Gu",
                            "value": "GU"
                        },
                        {
                            "displayValue": "Hawaii",
                            "value": "HI"
                        },
                        {
                            "displayValue": "Idaho",
                            "value": "ID"
                        },
                        {
                            "displayValue": "Illinois",
                            "value": "IL"
                        },
                        {
                            "displayValue": "Indiana",
                            "value": "IN"
                        },
                        {
                            "displayValue": "Iowa",
                            "value": "IA"
                        },
                        {
                            "displayValue": "Kansas",
                            "value": "KS"
                        },
                        {
                            "displayValue": "Kentucky",
                            "value": "KY"
                        },
                        {
                            "displayValue": "Louisiana",
                            "value": "LA"
                        },
                        {
                            "displayValue": "Maine",
                            "value": "ME"
                        },
                        {
                            "displayValue": "Marshall Islands",
                            "value": "MH"
                        },
                        {
                            "displayValue": "Maryland",
                            "value": "MD"
                        },
                        {
                            "displayValue": "Massachusetts",
                            "value": "MA"
                        },
                        {
                            "displayValue": "Michigan",
                            "value": "MI"
                        },
                        {
                            "displayValue": "Minnesota",
                            "value": "MN"
                        },
                        {
                            "displayValue": "Mississippi",
                            "value": "MS"
                        },
                        {
                            "displayValue": "Missouri",
                            "value": "MO"
                        },
                        {
                            "displayValue": "Montana",
                            "value": "MT"
                        },
                        {
                            "displayValue": "Nebraska",
                            "value": "NE"
                        },
                        {
                            "displayValue": "Nevada",
                            "value": "NV"
                        },
                        {
                            "displayValue": "New Hampshire",
                            "value": "NH"
                        },
                        {
                            "displayValue": "New Jersey",
                            "value": "NJ"
                        },
                        {
                            "displayValue": "New Mexico",
                            "value": "NM"
                        },
                        {
                            "displayValue": "New York",
                            "value": "NY"
                        },
                        {
                            "displayValue": "North Carolina",
                            "value": "NC"
                        },
                        {
                            "displayValue": "North Dakota",
                            "value": "ND"
                        },
                        {
                            "displayValue": "Northern Mariana Islands",
                            "value": "MP"
                        },
                        {
                            "displayValue": "Ohio",
                            "value": "OH"
                        },
                        {
                            "displayValue": "Oklahoma",
                            "value": "OK"
                        },
                        {
                            "displayValue": "Oregon",
                            "value": "OR"
                        },
                        {
                            "displayValue": "Palau",
                            "value": "PW"
                        },
                        {
                            "displayValue": "Pennsylvania",
                            "value": "PA"
                        },
                        {
                            "displayValue": "Puerto Rico",
                            "value": "PR"
                        },
                        {
                            "displayValue": "Rhode Island",
                            "value": "RI"
                        },
                        {
                            "displayValue": "South Carolina",
                            "value": "SC"
                        },
                        {
                            "displayValue": "South Dakota",
                            "value": "SD"
                        },
                        {
                            "displayValue": "Tennessee",
                            "value": "TN"
                        },
                        {
                            "displayValue": "Texas",
                            "value": "TX"
                        },
                        {
                            "displayValue": "Utah",
                            "value": "UT"
                        },
                        {
                            "displayValue": "Vermont",
                            "value": "VT"
                        },
                        {
                            "displayValue": "Virgin Islands",
                            "value": "VI"
                        },
                        {
                            "displayValue": "Virginia",
                            "value": "VA"
                        },
                        {
                            "displayValue": "Washington",
                            "value": "WA"
                        },
                        {
                            "displayValue": "West Virginia",
                            "value": "WV"
                        },
                        {
                            "displayValue": "Wisconsin",
                            "value": "WI"
                        },
                        {
                            "displayValue": "Wyoming",
                            "value": "WY"
                        }
                    ]
                },
                validation: {
                    rules: {
                    },
                    valid: true
                },
                value: 'AL'
            },
            zipCode: {
                elementType: 'input',
                elementConfig: {
                    type: 'text',
                    placeholder: 'ZIP code'
                },
                value: '',
                validation: {
                    rules: {
                        require: true,
                        minLength: 5,
                        maxLength: 5
                    },
                    valid: false
                },
                touch: false
            }
        },
        formIsValid: false,
        isSignUp: true
    }

    authHandler = (event) => {
        event.preventDefault();
        this.props.onAuth(this.state.controls['email'].value,
            this.state.controls['password'].value,
            this.state.isSignUp);
    }

    formElementValueChangedHandler = (event, formElementKey) => {
        const updatedOrderFormElement = updateObject(this.state.controls[formElementKey], {
            value: event.target.value,
            validation: updateObject(this.state.controls[formElementKey].validation, {
                valid: checkValidity(this.state.controls[formElementKey].validation.rules,
                    event.target.value)
            }),
            touch: true
        });

        const updatedOrderForm = updateObject(this.state.controls, {
            [formElementKey]: updatedOrderFormElement
        });

        let formIsValid = true;

        for (let formElementKey in updatedOrderForm) {
            formIsValid = updatedOrderForm[formElementKey].validation.valid && formIsValid;
        }

        this.setState({controls: updatedOrderForm, formIsValid: formIsValid});
    }

    switchModeHandler = () => {
        this.setState(prevState => {
            return {
                isSignUp: !prevState.isSignUp
            }
        });
    }

    componentDidMount() {
        if (!this.props.buildingBurger && this.props.authRedirectPath !== '/') {
            this.props.onSetAuthRedirectPath('/');
        }
    }

    render() {
        const formElementArray = [];

        for (let key in this.state.controls) {
            formElementArray.push({
                id: key,
                config: this.state.controls[key]
            })
        }

        let form = (
            <form onSubmit={this.authHandler}>
                {formElementArray.map(formElement => {
                    return <Input
                        key={formElement.id}
                        elementType={formElement.config.elementType}
                        elementConfig={formElement.config.elementConfig}
                        value={formElement.config.value}
                        valid={formElement.config.validation && formElement.config.validation.valid}
                        shouldValidate={formElement.config.validation}
                        touch={formElement.config.touch}
                        changed={(event) => this.formElementValueChangedHandler(event, formElement.id)}
                    />
                })}
                <Button btnType="Success" disabled={!this.state.formIsValid}>SUBMIT</Button>
            </form>
        );

        if (this.props.loading) {
            form = <Spinner/>;
        }

        let errorMgs = null;
        if (this.props.error) {
            errorMgs = <p style={{color: 'red'}}>{errors[this.props.error.message]}</p>;
        }

        let authRedirect = null;
        if (this.props.isAuthenticated) {
            authRedirect = <Redirect to={this.props.authRedirectPath} />
        }

        return (
            <div className={classes.Auth}>
                {authRedirect}
                {errorMgs}
                {form}
                <Button btnType="Danger" clicked={this.switchModeHandler}>Switch to {this.state.isSignUp ? "SIGN IN" : "SIGN UP"}</Button>
            </div>
        );
    }
}

const mapStateToProps = state => {
    return {
        loading: state.auth.loading,
        error: state.auth.error,
        isAuthenticated: state.auth.tokenId,
        buildingBurger: state.burgerBuilder.building,
        authRedirectPath: state.auth.authRedirectPath
    }
}

const mapDispatchToProps = dispatch => {
    return {
        onAuth: (email, password, isSignUp) => dispatch(actions.auth(email, password, isSignUp)),
        onSetAuthRedirectPath: (path) => dispatch(actions.setAuthRedirectPath(path))
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Auth);

