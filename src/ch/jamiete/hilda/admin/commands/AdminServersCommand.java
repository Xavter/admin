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
import ch.jamiete.hilda.commands.ChannelCommand;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.MessageBuilder.Formatting;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;

public class AdminServersCommand extends ChannelCommand {

    public AdminServersCommand(Hilda hilda) {
        super(hilda);

        this.setName("servers");
        this.setAliases(Arrays.asList(new String[] { "server", "serverlist", "listservers" }));
        this.setDescription("Lists the servers Hilda is on.");
    }

    @Override
    public void execute(Message message, String[] arguments, String label) {
        final MessageBuilder mb = new MessageBuilder();

        mb.append("I'm on " + this.hilda.getBot().getGuilds().size() + " servers:\n");

        for (final Guild guild : this.hilda.getBot().getGuilds()) {
            mb.append("\n");
            mb.append(guild.getName(), Formatting.BOLD);
            mb.append(" " + guild.getId() + " — ");
            mb.append(guild.getMembers().size() + " members, ");
            mb.append(guild.getTextChannels().size() + " text channels, ");
            mb.append(guild.getVoiceChannels().size() + " voice channels");
        }

        this.reply(message, mb.build());
    }

}
