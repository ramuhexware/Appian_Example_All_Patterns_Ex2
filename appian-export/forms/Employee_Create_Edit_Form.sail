/* Appian SAIL Interface Form: Employee Create / Edit Form */
a!formLayout(
  label: "Enterprise Employee Management - New Record",
  instructions: "Fill in employee details and save to submit into Appian process workflow",
  contents: {
    a!sectionLayout(
      label: "Personal Information",
      contents: {
        a!textField(
          label: "First Name",
          value: ri!employee.firstName,
          saveInto: ri!employee.firstName,
          required: true()
        ),
        a!textField(
          label: "Last Name",
          value: ri!employee.lastName,
          saveInto: ri!employee.lastName,
          required: true()
        ),
        a!textField(
          label: "Email Address",
          value: ri!employee.email,
          saveInto: ri!employee.email,
          required: true(),
          validations: {
            if(
              len(ri!employee.email) > 0 and not(regexmatch("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", ri!employee.email)),
              "Please enter a valid email address",
              null
            )
          }
        )
      }
    ),
    a!sectionLayout(
      label: "Job & Compensation Details",
      contents: {
        a!textField(
          label: "Job Title",
          value: ri!employee.title,
          saveInto: ri!employee.title
        ),
        a!dropdownField(
          label: "Department",
          choiceLabels: {"Human Resources", "Software Engineering", "Finance & Operations"},
          choiceValues: {"DEP-101", "DEP-102", "DEP-103"},
          value: ri!employee.departmentId,
          saveInto: ri!employee.departmentId
        ),
        a!floatingPointField(
          label: "Base Annual Salary ($)",
          value: ri!employee.salary,
          saveInto: ri!employee.salary
        )
      }
    )
  },
  buttons: a!buttonLayout(
    primaryButtons: {
      a!buttonWidget(
        label: "Submit Employee Record",
        submit: true(),
        style: "PRIMARY"
      )
    },
    secondaryButtons: {
      a!buttonWidget(
        label: "Cancel",
        value: true(),
        saveInto: ri!cancel,
        submit: true(),
        style: "DESTRUCTIVE"
      )
    }
  )
)
