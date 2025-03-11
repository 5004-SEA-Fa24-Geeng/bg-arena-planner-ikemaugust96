# Report

Submitted report to be manually graded. We encourage you to review the report as you read through the provided
code as it is meant to help you understand some of the concepts. 

## Technical Questions

1. What is the difference between == and .equals in java? Provide a code example of each, where they would return different results for an object. Include the code snippet using the hash marks (```) to create a code block.
   ```java
   // your code here
   public class EqualsVsDoubleEquals {
    public static void main(String[] args) {
        // Case 1: String comparison
        String s1 = new String("Hello");
        String s2 = new String("Hello");

        System.out.println(s1 == s2);      // ❌ false, different objects in memory
        System.out.println(s1.equals(s2)); // ✅ true, same content

        // Case 2: Custom object comparison
        Person p1 = new Person("Alice");
        Person p2 = new Person("Alice");

        System.out.println(p1 == p2);      // ❌ false, different objects
        System.out.println(p1.equals(p2)); // ❌ false, default .equals() checks memory

        // Case 3: Custom object with overridden equals()
        PersonOverridden p3 = new PersonOverridden("Alice");
        PersonOverridden p4 = new PersonOverridden("Alice");

        System.out.println(p3 == p4);      // ❌ false, different objects
        System.out.println(p3.equals(p4)); // ✅ true, compares name values
      }
    }

    class Person {
      String name;
      public Person(String name) { this.name = name; }
    }

    class PersonOverridden {
      String name;
      public PersonOverridden(String name) { this.name = name; }

      @Override
      public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof PersonOverridden other) {
            return this.name.equals(other.name);
        }
        return false;
     }
   }

   ```

== checks whether two references point to the same memory location (i.e., whether they are the same object).
.equals() is a method that compares the contents (or values) of objects. By default, if not overridden, .equals() behaves like ==, but many classes (like String) override it to check for logical equality instead.




2. Logical sorting can be difficult when talking about case. For example, should "apple" come before "Banana" or after? How would you sort a list of strings in a case-insensitive manner? 

Logical sorting with case differences can be tricky because Java's default sorting treats uppercase letters before lowercase ones (e.g., "Banana" comes before "apple"). To ensure case-insensitive sorting, you can use String.CASE_INSENSITIVE_ORDER, which is a built-in comparator that ignores case, or Comparator.comparing(String::toLowerCase), which explicitly converts each string to lowercase before comparison. Both methods ensure alphabetical sorting while treating "apple" and "Apple" as equal.




3. In our version of the solution, we had the following code (snippet)
    ```java
    public static Operations getOperatorFromStr(String str) {
        if (str.contains(">=")) {
            return Operations.GREATER_THAN_EQUALS;
        } else if (str.contains("<=")) {
            return Operations.LESS_THAN_EQUALS;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("=="))...
    ```
    Why would the order in which we checked matter (if it does matter)? Provide examples either way proving your point. 

The order of checks in the getOperatorFromStr method does matter because some operators contain substrings of other operators. If we check in the wrong order, we might incorrectly match part of a longer operator before recognizing the full operator.
Consider the operators >= and >. If we check for ">" before ">=", the method would detect just ">" first and return Operations.GREATER_THAN, even when the actual input was ">=".

4. What is the difference between a List and a Set in Java? When would you use one over the other? 

In Java, List and Set are both part of the Collection framework, but they serve different purposes and have distinct characteristics.

A List is an ordered collection that allows duplicates. Elements are stored in a sequence, and each has an index. This makes List ideal when ordering matters or when you need to access elements by position. Common implementations include ArrayList (fast random access) and LinkedList (fast insertions/removals in the middle).

A Set, on the other hand, is an unordered collection that does not allow duplicates. It is useful when uniqueness is required, such as storing a set of unique usernames or distinct items in a game. Popular implementations include HashSet (fast lookups but no order), LinkedHashSet (preserves insertion order), and TreeSet (sorted order).


5. In [GamesLoader.java](src/main/java/student/GamesLoader.java), we use a Map to help figure out the columns. What is a map? Why would we use a Map here? 




6. [GameData.java](src/main/java/student/GameData.java) is actually an `enum` with special properties we added to help with column name mappings. What is an `enum` in Java? Why would we use it for this application?







7. Rewrite the following as an if else statement inside the empty code block.
    ```java
    switch (ct) {
                case CMD_QUESTION: // same as help
                case CMD_HELP:
                    processHelp();
                    break;
                case INVALID:
                default:
                    CONSOLE.printf("%s%n", ConsoleText.INVALID);
            }
    ``` 

    ```java
    // your code here, don't forget the class name that is dropped in the switch block..
    
    ```

## Deeper Thinking

ConsoleApp.java uses a .properties file that contains all the strings
that are displayed to the client. This is a common pattern in software development
as it can help localize the application for different languages. You can see this
talked about here on [Java Localization – Formatting Messages](https://www.baeldung.com/java-localization-messages-formatting).

Take time to look through the console.properties file, and change some of the messages to
another language (probably the welcome message is easier). It could even be a made up language and for this - and only this - alright to use a translator. See how the main program changes, but there are still limitations in 
the current layout. 

Post a copy of the run with the updated languages below this. Use three back ticks (```) to create a code block. 

```text
// your consoles output here
```

Now, thinking about localization - we have the question of why does it matter? The obvious
one is more about market share, but there may be other reasons.  I encourage
you to take time researching localization and the importance of having programs
flexible enough to be localized to different languages and cultures. Maybe pull up data on the
various spoken languages around the world? What about areas with internet access - do they match? Just some ideas to get you started. Another question you are welcome to talk about - what are the dangers of trying to localize your program and doing it wrong? Can you find any examples of that? Business marketing classes love to point out an example of a car name in Mexico that meant something very different in Spanish than it did in English - however [Snopes has shown that is a false tale](https://www.snopes.com/fact-check/chevrolet-nova-name-spanish/).  As a developer, what are some things you can do to reduce 'hick ups' when expanding your program to other languages?


As a reminder, deeper thinking questions are meant to require some research and to be answered in a paragraph for with references. The goal is to open up some of the discussion topics in CS, so you are better informed going into industry. 
