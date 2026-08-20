/* Appian SAIL Interface Form: Employee Record Summary Dashboard */
a!dashboardLayout(
  contents: {
    a!columnsLayout(
      columns: {
        a!columnLayout(
          contents: {
            a!cardLayout(
              contents: {
                a!richTextDisplayField(
                  value: {
                    a!richTextItem(text: "Total Employees", style: "SECONDARY"),
                    char(10),
                    a!richTextItem(text: "1,248", size: "LARGE_HEADER", style: "STRONG")
                  }
                )
              }
            )
          }
        ),
        a!columnLayout(
          contents: {
            a!cardLayout(
              contents: {
                a!richTextDisplayField(
                  value: {
                    a!richTextItem(text: "Active Department Count", style: "SECONDARY"),
                    char(10),
                    a!richTextItem(text: "14", size: "LARGE_HEADER", style: "STRONG")
                  }
                )
              }
            )
          }
        )
      }
    ),
    a!gridField(
      label: "Enterprise Employee Directory",
      data: ri!employeeList,
      columns: {
        a!gridColumn(
          label: "Employee ID",
          value: fv!row.employeeId
        ),
        a!gridColumn(
          label: "Name",
          value: fv!row.firstName & " " & fv!row.lastName
        ),
        a!gridColumn(
          label: "Email",
          value: fv!row.email
        ),
        a!gridColumn(
          label: "Department",
          value: fv!row.departmentId
        ),
        a!gridColumn(
          label: "Status",
          value: a!tagItem(
            text: fv!row.status,
            backgroundColor: if(fv!row.status = "ACTIVE", "#E6F4EA", "#FCE8E6")
          )
        )
      }
    )
  }
)
