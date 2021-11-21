package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class team implements Comparable<team>
{
    private String name;
    private ArrayList<Integer> goal;
    private int total_score;
    private int total_goal;
    private int total_goal_lost;

    public team(String name, ArrayList<Integer> goal, int total_goal)
    {
        this.name = name;
        this.goal = goal;
        this.total_score = 0;
        this.total_goal_lost = 0;
        this.total_goal = total_goal;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Integer> getGoal()
    {
        return goal;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public void setTotal_goal_lost(int total_goal_lost) {
        this.total_goal_lost = total_goal_lost;
    }

    @Override
    public int compareTo(team o) {

        if(this.total_score == o.total_score && this.total_goal_lost == o.total_goal_lost)
        {
            return o.total_goal - this.total_goal;
        }
        else if(this.total_score == o.total_score)
        {
            return o.total_goal_lost - this.total_goal_lost;
        }
        else
        {
            return o.total_score - this.total_score;
        }
    }
}

class Main
{
    public ArrayList<team> calculate_score(ArrayList<team> teams)
    {
        for (int i=0;i<4;i++)
        {
            int total_score = 0;
            int total_goal_lost = 0;
            for (int j=0;j<4;j++)
            {
                if(i!=j)
                {
                    if(teams.get(i).getGoal().get(j) > teams.get(j).getGoal().get(i))
                    {
                        total_score += 3;
                    }
                    else if(teams.get(i).getGoal().get(j) == teams.get(j).getGoal().get(i))
                    {
                        total_score++;
                    }

                    total_goal_lost += teams.get(i).getGoal().get(j) - teams.get(j).getGoal().get(i);
                }
            }

            teams.get(i).setTotal_score(total_score);
            teams.get(i).setTotal_goal_lost(total_goal_lost);
        }

        return teams;
    }

    public static void main(String[] args)
    {
        Main m = new Main();

        Scanner input = new Scanner(System.in);
        ArrayList<String> name = new ArrayList<String>();
        ArrayList<team> teams = new ArrayList<team>();

        for(int i=0;i<4;i++)
        {
            String buf = input.next();
            if(buf.length()>20)
            {
                System.exit(0);
            }
            name.add(buf);
        }

        for(int i=0;i<4;i++)
        {
            int total_goal = 0;
            ArrayList<Integer> goal = new ArrayList<Integer>();
            for(int j=0;j<4;j++)
            {
                int buf = input.nextInt();
                goal.add(buf);
                total_goal += buf;
            }
            teams.add(new team(name.get(i),goal,total_goal));
        }

        teams = m.calculate_score(teams);
        Collections.sort(teams);

        for (team t: teams)
        {
            System.out.println(t.getName() + " " + t.getTotal_score());
        }
    }
}
