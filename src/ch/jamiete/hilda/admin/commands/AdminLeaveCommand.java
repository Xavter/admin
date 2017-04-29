/*******************************************************************************
 * Copyright 2017 jamietech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package ch.jamiete.hilda.admin.commands;

import java.util.Arrays;
import ch.jamiete.hilda.Hilda;
import ch.jamiete.hilda.admin.AdminPlugin;
import ch.jamiete.hilda.commands.ChannelCommand;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;

public class AdminLeaveCommand extends ChannelCommand {
    private AdminPlugin plugin;

    protected AdminLeaveCommand(Hilda hilda, AdminPlugin plugin) {
        super(hilda);

        this.plugin = plugin;

        this.setName("leave");
        this.setAliases(Arrays.asList(new String[] { "quit", "part" }));
        this.setDescription("Leaves a server.");
    }

    @Override
    public void execute(Message message, String[] arguments, String label) {
        if (!this.plugin.canRun(message)) {
            return;
        }

        if (arguments.length != 1) {
            this.usage(message, "leave <id>", label);
            return;
        }

        final Guild guild = this.hilda.getBot().getGuildById(arguments[0]);

        if (guild == null) {
            this.reply(message, "I'm not on that server.");
        } else {
            guild.leave().queue();
            this.reply(message, "I've left " + guild.getName() + ".");
        }
    }

}