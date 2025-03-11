# Board Game Arena Planner Design Document


This document is meant to provide a tool for you to demonstrate the design process. You need to work on this before you code, and after have a finished product. That way you can compare the changes, and changes in design are normal as you work through a project. It is contrary to popular belief, but we are not perfect our first attempt. We need to iterate on our designs to make them better. This document is a tool to help you do that.


## (INITIAL DESIGN): Class Diagram 

Place your class diagrams below. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. If it is not, you will need to fix it. As a reminder, here is a link to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

### Provided Code

Provide a class diagram for the provided code as you read through it.  For the classes you are adding, you will create them as a separate diagram, so for now, you can just point towards the interfaces for the provided code diagram.



### Your Plans/Design

Create a class diagram for the classes you plan to create. This is your initial design, and it is okay if it changes. Your starting points are the interfaces. 

![UML Diagram]![program](https://github.com/user-attachments/assets/f7721709-c1c0-420a-a771-535b26411fd3)



## (INITIAL DESIGN): Tests to Write - Brainstorm

Write a test (in english) that you can picture for the class diagram you have created. This is the brainstorming stage in the TDD process. 

> [!TIP]
> As a reminder, this is the TDD process we are following:
> 1. Figure out a number of tests by brainstorming (this step)
> 2. Write **one** test
> 3. Write **just enough** code to make that test pass
> 4. Refactor/update  as you go along
> 5. Repeat steps 2-4 until you have all the tests passing/fully built program

You should feel free to number your brainstorm. 

1. Tests for FilterParser:
Single Condition Filter Test

Verify that a single condition like minPlayers>4 is correctly parsed and applied.
Example: Only games with minPlayers > 4 are included.
Multiple Condition Filter Test

Test comma-separated filters like minPlayers>4,maxPlayers<6.
Confirm that both conditions are applied using an AND operation.

2. Tests for FilterParser:
Multiple Condition Filter Test

Test comma-separated filters like minPlayers>4,maxPlayers<6.
Confirm that both conditions are applied using an AND operation.

3.Tests for GameSorter:
Sort by Name (Ascending)

Verify that games are sorted by name in ascending order.
Case insensitive: "catan", "Pandemic", "Zombicide".

4.Tests for GameSorter:
Sort by Name (Descending)

Confirm descending order sorting for name.
Case insensitive: "Zombicide", "Pandemic", "catan".

5.Tests for GameListManager:
Add Game Test

Confirm that a game is added to the list successfully.
Example: Adding "Catan" results in the list containing "Catan".

6.Tests for GameListManager:

Add Duplicate Game Test (Case Insensitive)

Ensure duplicates are not added (case insensitive).
Example: Adding "Catan" and "catan" should result in only one entry.




## (FINAL DESIGN): Class Diagram

Go through your completed code, and update your class diagram to reflect the final design. Make sure you check the file in the browser on github.com to make sure it is rendering correctly. It is normal that the two diagrams don't match! Rarely (though possible) is your initial design perfect. 

For the final design, you just need to do a single diagram that includes both the original classes and the classes you added. 

> [!WARNING]
> If you resubmit your assignment for manual grading, this is a section that often needs updating. You should double check with every resubmit to make sure it is up to date.
![UML Diagram]![program]![bg-arena-planner-ikemaugust96](https://github.com/user-attachments/assets/a2fdc9b5-9dba-45d7-97bb-2f1371386c9b)




## (FINAL DESIGN): Reflection/Retrospective

> [!IMPORTANT]
> The value of reflective writing has been highly researched and documented within computer science, from learning to information to showing higher salaries in the workplace. For this next part, we encourage you to take time, and truly focus on your retrospective.

Take time to reflect on how your design has changed. Write in *prose* (i.e. do not bullet point your answers - it matters in how our brain processes the information). Make sure to include what were some major changes, and why you made them. What did you learn from this process? What would you do differently next time? What was the most challenging part of this process? For most students, it will be a paragraph or two. 

Reflecting on the design process, I initially assumed that the sorting issue stemmed from String.CASE_INSENSITIVE_ORDER, but in reality, the root cause was overlooking the filtering mechanism within the Planner class. This oversight led to incorrect assumptions about the order of games in getGameNames(), when in fact, the actual problem was that the filtered set of games was not being properly processed before being stored or retrieved. This realization highlights how debugging often requires stepping back and re-evaluating assumptions, rather than focusing on the most immediate suspect.

Throughout this process, I learned that even a seemingly minor oversight—such as neglecting to apply filtering consistently—can have ripple effects throughout the system. This experience reinforced the importance of systematically verifying how data flows through different components of the program, rather than making changes based solely on observed symptoms. If I were to approach this differently next time, I would pay closer attention to how data is transformed at each stage, ensuring that all intermediary steps (such as filtering, sorting, and storage) align with the intended logic. The most challenging aspect of this was that the error appeared in a location that seemed completely unrelated to filtering, making it easy to be misled by initial test failures. However, the process of debugging and fixing this mistake has given me a deeper appreciation for rigorous testing and the importance of verifying assumptions before jumping to conclusions.
