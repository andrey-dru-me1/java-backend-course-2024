package edu.java.bot.db.emulation;

import java.util.HashMap;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class Links extends HashMap<Long, Set<String>> {}
