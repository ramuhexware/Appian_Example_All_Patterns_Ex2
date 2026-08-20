/* Appian SAIL Interface Form: Manager Employee Approval Task Form */
a!formLayout(
  label: "Manager Approval Task: New Employee Onboarding",
  instructions: "Review candidate information and provide approval for process workflow continuation",
  contents: {
    a!textField(
      label: "Candidate Name",
      value: ri!employee.firstName & " " & ri!employee.lastName,
      readOnly: true()
    ),
    a!textField(
      label: "Designated Title",
      value: ri!employee.title,
      readOnly: true()
    ),
    a!paragraphField(
      label: "Approval Comments",
      value: ri!comments,
      saveInto: ri!comments,
      required: true()
    )
  },
  buttons: a!buttonLayout(
    primaryButtons: {
      a!buttonWidget(
        label: "Approve Onboarding",
        value: "APPROVED",
        saveInto: ri!approvalDecision,
        submit: true(),
        style: "PRIMARY"
      )
    },
    secondaryButtons: {
      a!buttonWidget(
        label: "Reject Request",
        value: "REJECTED",
        saveInto: ri!approvalDecision,
        submit: true(),
        style: "DESTRUCTIVE"
      )
    }
  )
)
