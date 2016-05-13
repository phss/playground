import curses

stdscr = curses.initscr()
stdscr.addstr(0, 0, "Current mode: Typing mode",
                  curses.A_REVERSE)
stdscr.refresh()
c = stdscr.getch()
curses.endwin()
