import React from 'react';
import {render} from 'react-dom';

class Employee extends React.Component {
	render() {
		console.log("Employee::render()");
		console.log(this.props);
		return (
			<tr>
				<td>{this.props.employeeObject['id']}</td>
				<td>{this.props.employeeObject['fullname']}</td>
				<td>{this.props.employeeObject['shift']}</td>
				<td>BUTTONS TO ADD</td>
			</tr>
		);
	}
}

class EmployeeList extends React.Component {
	render() {
		console.log("EmployeeList::render()");
		console.log(this.props.employeeList);

		var employeeList = Object.values(this.props.employeeList).map(employeeObject =>
			<Employee employeeObject = {JSON.parse(employeeObject)}/>
		);

		return(
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Shift</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					{employeeList}
				</tbody>
			</table>
		);
	}
}

class ErrorComponent extends React.Component {
	render() {
		return(<p>An error occured.</p>);
	}
}

class App extends React.Component {
    constructor(props) {
		console.log("App::constructor(props)");
        super(props);
    }
    
    render() {
		console.log("App::render()");
		
    	var employeeList = {};
    	var finalResult = React.Component();
    	
    	$.ajax({
    		method: "GET",
            async: false,
    		url: "./api/employees",
    	}).done(function(msg) {
        	console.log("Done fetching EmployeeList");
    		employeeList = JSON.parse(msg);
    		console.log(employeeList);

    		if(typeof employeeList['error'] != "undefined") { 
    			employeeList = {};
    			
    			$('#error-modal').modal('open');
    			$("#error-text").text(employeeList['error']);
    			
            	console.log("Returning ErrorComponent");
            	finalResult = <ErrorComponent />;
    		}

        	console.log("Returning EmployeeList");
            finalResult = <EmployeeList employeeList = {employeeList}/>;
    	});
        
    	console.log(finalResult);
        return(finalResult);
    }
}

render(<App />,	document.getElementById('target'));