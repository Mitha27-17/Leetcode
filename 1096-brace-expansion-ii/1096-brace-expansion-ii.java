import java.util.*;

class Solution {

    private TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(set);
    }

    private void dfs(String exp) {

        // Find the first closing brace
        int close = exp.indexOf('}');

        // No braces left
        if (close == -1) {
            set.add(exp);
            return;
        }

        // Find the matching opening brace
        int open = exp.lastIndexOf('{', close);

        String before = exp.substring(0, open);
        String inside = exp.substring(open + 1, close);
        String after = exp.substring(close + 1);

        // Split the choices by comma
        String[] choices = inside.split(",");

        // Try every choice
        for (String choice : choices) {
            dfs(before + choice + after);
        }
    }
}